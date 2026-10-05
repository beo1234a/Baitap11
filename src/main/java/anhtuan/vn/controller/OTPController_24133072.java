package anhtuan.vn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import anhtuan.vn.service.IUserService_24133072;
import anhtuan.vn.service.UserService_24133072;

@WebServlet(urlPatterns = { "/verify-otp" })
public class OTPController_24133072 extends HttpServlet {

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

        if (session == null
                || session.getAttribute("registerUsername") == null
                || session.getAttribute("registerOTP") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/register");

            return;
        }

        request.getRequestDispatcher(
                "/views/auth/verify-otp.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session =
                request.getSession(false);

        if (session == null) {
            response.sendRedirect(
                    request.getContextPath() + "/register");
            return;
        }

        String inputOTP =
                request.getParameter("otp");

        String savedOTP =
                (String) session.getAttribute(
                        "registerOTP");

        String username =
                (String) session.getAttribute(
                        "registerUsername");

        if (inputOTP == null
                || savedOTP == null
                || username == null
                || !savedOTP.equals(inputOTP.trim())) {

            request.setAttribute(
                    "error",
                    "Mã OTP không chính xác.");

            request.getRequestDispatcher(
                    "/views/auth/verify-otp.jsp")
                    .forward(request, response);

            return;
        }

        boolean activated =
                userService.activate(username);

        if (!activated) {

            request.setAttribute(
                    "error",
                    "Không thể kích hoạt tài khoản.");

            request.getRequestDispatcher(
                    "/views/auth/verify-otp.jsp")
                    .forward(request, response);

            return;
        }

        session.removeAttribute("registerOTP");
        session.removeAttribute("registerUsername");

        session.setAttribute(
                "message",
                "Kích hoạt tài khoản thành công. Vui lòng đăng nhập.");

        response.sendRedirect(
                request.getContextPath() + "/login");
    }
}