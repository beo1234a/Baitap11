package anhtuan.vn.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import anhtuan.vn.entity.Order_24133072;
import anhtuan.vn.entity.User_24133072;
import anhtuan.vn.model.CartItem_24133072;
import anhtuan.vn.service.IOrderService_24133072;
import anhtuan.vn.service.OrderService_24133072;

@WebServlet(urlPatterns = { "/checkout" })
public class CheckoutController_24133072 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final IOrderService_24133072 orderService =
            new OrderService_24133072();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        User_24133072 account = getUser(request, response);
        if (account == null) {
            return;
        }

        if ("1".equals(request.getParameter("success"))) {
            HttpSession session = request.getSession();
            Order_24133072 order =
                    (Order_24133072) session.getAttribute("checkoutSuccessOrder");
            session.removeAttribute("checkoutSuccessOrder");

            if (order != null) {
                request.setAttribute("order", order);
                request.getRequestDispatcher("/views/user/checkout-success.jsp")
                        .forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/orders");
            }
            return;
        }

        Map<String, CartItem_24133072> cart = getCart(request.getSession());
        if (cart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        prepareCheckout(request, account, cart);
        request.getRequestDispatcher("/views/user/checkout.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        User_24133072 account = getUser(request, response);
        if (account == null) {
            return;
        }

        Map<String, CartItem_24133072> cart = getCart(request.getSession());
        if (cart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        String receiverName = trim(request.getParameter("receiverName"));
        String phone = trim(request.getParameter("phone"));
        String address = trim(request.getParameter("address"));

        if (receiverName.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ người nhận, số điện thoại và địa chỉ giao hàng.");
            request.setAttribute("receiverName", receiverName);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            prepareCheckout(request, account, cart);
            request.getRequestDispatcher("/views/user/checkout.jsp")
                    .forward(request, response);
            return;
        }

        try {
            Order_24133072 order = orderService.createCodOrder(
                    account.getUsername(),
                    receiverName,
                    phone,
                    address,
                    cart);

            HttpSession session = request.getSession();
            session.removeAttribute("cart");
            session.setAttribute("cartCount", 0);
            session.setAttribute("checkoutSuccessOrder", order);

            response.sendRedirect(
                    request.getContextPath() + "/checkout?success=1");

        } catch (IllegalStateException e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("receiverName", receiverName);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            prepareCheckout(request, account, cart);
            request.getRequestDispatcher("/views/user/checkout.jsp")
                    .forward(request, response);
        }
    }

    private void prepareCheckout(
            HttpServletRequest request,
            User_24133072 account,
            Map<String, CartItem_24133072> cart) {

        BigDecimal total = BigDecimal.ZERO;
        for (CartItem_24133072 item : cart.values()) {
            total = total.add(item.getSubtotal());
        }

        request.setAttribute("cartItems", cart.values());
        request.setAttribute("cartTotal", total);

        if (request.getAttribute("receiverName") == null) {
            request.setAttribute("receiverName", account.getFullname());
        }
        if (request.getAttribute("phone") == null) {
            request.setAttribute("phone", account.getPhone());
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, CartItem_24133072> getCart(HttpSession session) {
        Object value = session.getAttribute("cart");
        if (value instanceof Map) {
            return (Map<String, CartItem_24133072>) value;
        }
        Map<String, CartItem_24133072> cart = new LinkedHashMap<>();
        session.setAttribute("cart", cart);
        return cart;
    }

    private User_24133072 getUser(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);
        User_24133072 account = session == null
                ? null
                : (User_24133072) session.getAttribute("account");

        if (account == null || Boolean.TRUE.equals(account.getAdmin())) {
            response.sendRedirect(request.getContextPath() + "/login");
            return null;
        }
        return account;
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }
}
