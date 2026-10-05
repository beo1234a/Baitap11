package anhtuan.vn.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import anhtuan.vn.entity.User_24133072;
import anhtuan.vn.entity.Video_24133072;
import anhtuan.vn.model.CartItem_24133072;
import anhtuan.vn.service.IVideoService_24133072;
import anhtuan.vn.service.VideoService_24133072;

@WebServlet(urlPatterns = {
        "/cart",
        "/cart/add",
        "/cart/update",
        "/cart/remove",
        "/cart/clear"
})
public class CartController_24133072 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final IVideoService_24133072 videoService =
            new VideoService_24133072();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        if (!requireUser(request, response)) {
            return;
        }

        showCart(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        if (!requireUser(request, response)) {
            return;
        }

        String path = request.getServletPath();

        if ("/cart/add".equals(path)) {
            add(request);
        } else if ("/cart/update".equals(path)) {
            update(request);
        } else if ("/cart/remove".equals(path)) {
            remove(request);
        } else if ("/cart/clear".equals(path)) {
            clear(request);
        }

        response.sendRedirect(request.getContextPath() + "/cart");
    }

    private void add(HttpServletRequest request) {
        String videoId = request.getParameter("videoId");
        int requested = parsePositiveInt(request.getParameter("quantity"), 1);

        Video_24133072 video = videoService.findById(videoId);
        if (video == null || !Boolean.TRUE.equals(video.getActive())) {
            setFlash(request, "error", "Sản phẩm không tồn tại hoặc đã ngừng bán.");
            return;
        }

        int stock = video.getQuantity() == null ? 0 : video.getQuantity();
        if (stock < 1) {
            setFlash(request, "error", "Sản phẩm đã hết hàng.");
            return;
        }

        Map<String, CartItem_24133072> cart = getCart(request.getSession());
        CartItem_24133072 current = cart.get(videoId);
        int newQuantity = requested + (current == null ? 0 : current.getQuantity());

        if (newQuantity > stock) {
            newQuantity = stock;
            setFlash(request, "message",
                    "Số lượng đã được giới hạn theo tồn kho hiện tại: " + stock + ".");
        } else {
            setFlash(request, "message", "Đã thêm sản phẩm vào giỏ hàng.");
        }

        cart.put(videoId, new CartItem_24133072(video, newQuantity));
        updateCartCount(request.getSession(), cart.values());
    }

    private void update(HttpServletRequest request) {
        String videoId = request.getParameter("videoId");
        Map<String, CartItem_24133072> cart = getCart(request.getSession());

        if (!cart.containsKey(videoId)) {
            return;
        }

        Video_24133072 video = videoService.findById(videoId);
        if (video == null) {
            cart.remove(videoId);
            updateCartCount(request.getSession(), cart.values());
            setFlash(request, "error", "Sản phẩm không còn tồn tại và đã được xóa khỏi giỏ.");
            return;
        }

        int stock = video.getQuantity() == null ? 0 : video.getQuantity();
        if (stock < 1) {
            cart.remove(videoId);
            updateCartCount(request.getSession(), cart.values());
            setFlash(request, "error", "Sản phẩm đã hết hàng và được xóa khỏi giỏ.");
            return;
        }

        int requested = parsePositiveInt(request.getParameter("quantity"), 1);
        int accepted = Math.max(1, Math.min(requested, stock));

        cart.put(videoId, new CartItem_24133072(video, accepted));
        updateCartCount(request.getSession(), cart.values());

        if (requested != accepted) {
            setFlash(request, "message",
                    "Số lượng hợp lệ từ 1 đến " + stock + ". Hệ thống đã điều chỉnh lại.");
        } else {
            setFlash(request, "message", "Đã cập nhật số lượng.");
        }
    }

    private void remove(HttpServletRequest request) {
        String videoId = request.getParameter("videoId");
        Map<String, CartItem_24133072> cart = getCart(request.getSession());
        cart.remove(videoId);
        updateCartCount(request.getSession(), cart.values());
        setFlash(request, "message", "Đã xóa sản phẩm khỏi giỏ hàng.");
    }

    private void clear(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("cart");
        session.setAttribute("cartCount", 0);
        setFlash(request, "message", "Đã xóa toàn bộ giỏ hàng.");
    }

    private void showCart(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Map<String, CartItem_24133072> cart = getCart(session);

        for (String videoId : new java.util.ArrayList<>(cart.keySet())) {
            CartItem_24133072 oldItem = cart.get(videoId);
            Video_24133072 freshVideo = videoService.findById(videoId);

            if (freshVideo == null
                    || !Boolean.TRUE.equals(freshVideo.getActive())
                    || freshVideo.getQuantity() == null
                    || freshVideo.getQuantity() < 1) {
                cart.remove(videoId);
                continue;
            }

            int accepted = Math.min(
                    oldItem.getQuantity(),
                    freshVideo.getQuantity());
            accepted = Math.max(1, accepted);
            cart.put(videoId, new CartItem_24133072(freshVideo, accepted));
        }
        updateCartCount(session, cart.values());

        String message = (String) session.getAttribute("cartMessage");
        String error = (String) session.getAttribute("cartError");
        session.removeAttribute("cartMessage");
        session.removeAttribute("cartError");

        BigDecimal total = BigDecimal.ZERO;
        for (CartItem_24133072 item : cart.values()) {
            total = total.add(item.getSubtotal());
        }

        request.setAttribute("cartItems", cart.values());
        request.setAttribute("cartTotal", total);
        request.setAttribute("message", message);
        request.setAttribute("error", error);

        request.getRequestDispatcher("/views/user/cart.jsp")
                .forward(request, response);
    }

    @SuppressWarnings("unchecked")
    private Map<String, CartItem_24133072> getCart(HttpSession session) {
        Object value = session.getAttribute("cart");
        if (value instanceof Map) {
            return (Map<String, CartItem_24133072>) value;
        }

        Map<String, CartItem_24133072> cart = new LinkedHashMap<>();
        session.setAttribute("cart", cart);
        session.setAttribute("cartCount", 0);
        return cart;
    }

    private void updateCartCount(
            HttpSession session,
            Collection<CartItem_24133072> items) {
        int count = 0;
        for (CartItem_24133072 item : items) {
            count += item.getQuantity();
        }
        session.setAttribute("cartCount", count);
    }

    private void setFlash(
            HttpServletRequest request,
            String type,
            String value) {
        HttpSession session = request.getSession();
        if ("error".equals(type)) {
            session.setAttribute("cartError", value);
        } else {
            session.setAttribute("cartMessage", value);
        }
    }

    private boolean requireUser(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);
        User_24133072 account = session == null
                ? null
                : (User_24133072) session.getAttribute("account");

        if (account == null || Boolean.TRUE.equals(account.getAdmin())) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }

    private int parsePositiveInt(String value, int defaultValue) {
        try {
            int number = Integer.parseInt(value);
            return number > 0 ? number : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
