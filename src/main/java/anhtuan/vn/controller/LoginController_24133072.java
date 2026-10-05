package anhtuan.vn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import anhtuan.vn.entity.User_24133072;
import anhtuan.vn.service.IUserService_24133072;
import anhtuan.vn.service.UserService_24133072;

@WebServlet(urlPatterns = { "/login" })
public class LoginController_24133072 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final IUserService_24133072 userService =
            new UserService_24133072();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session != null) {

            String message =
                    (String) session.getAttribute(
                            "message");

            if (message != null) {
                request.setAttribute(
                        "message",
                        message);

                session.removeAttribute(
                        "message");
            }
        }

        request.getRequestDispatcher(
                "/views/auth/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username =
                request.getParameter("username");

        String password =
                request.getParameter("password");

        User_24133072 user =
                userService.login(
                        username,
                        password);

        if (user == null) {

            request.setAttribute(
                    "error",
                    "Tên đăng nhập, mật khẩu không đúng hoặc tài khoản chưa kích hoạt.");

            request.getRequestDispatcher(
                    "/views/auth/login.jsp")
                    .forward(request, response);

            return;
        }

        HttpSession session =
                request.getSession();

        session.setAttribute(
                "account",
                user);

        if (Boolean.TRUE.equals(
                user.getAdmin())) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/home");

        } else {

            response.sendRedirect(
                    request.getContextPath()
                    + "/home");
        }
    }
}