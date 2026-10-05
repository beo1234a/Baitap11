package anhtuan.vn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import anhtuan.vn.entity.Order_24133072;
import anhtuan.vn.entity.User_24133072;
import anhtuan.vn.service.IOrderService_24133072;
import anhtuan.vn.service.OrderService_24133072;
import anhtuan.vn.util.OrderStatus_24133072;

@WebServlet(urlPatterns = { "/orders" })
public class OrderHistoryController_24133072 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final IOrderService_24133072 orderService =
            new OrderService_24133072();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User_24133072 account = session == null
                ? null
                : (User_24133072) session.getAttribute("account");

        if (account == null || Boolean.TRUE.equals(account.getAdmin())) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String status = request.getParameter("status");
        if (!OrderStatus_24133072.isValid(status)) {
            status = null;
        }

        List<Order_24133072> orders = orderService.findByUser(
                account.getUsername(),
                status);

        request.setAttribute("orders", orders);
        request.setAttribute("selectedStatus", status == null ? "" : status);
        request.setAttribute("statusLabels", OrderStatus_24133072.getLabels());

        request.getRequestDispatcher("/views/user/order-history.jsp")
                .forward(request, response);
    }
}
