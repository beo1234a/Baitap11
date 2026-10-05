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
import anhtuan.vn.util.EmailUtil_24133072;
import anhtuan.vn.util.OTPUtil_24133072;

@WebServlet(urlPatterns = { "/register" })
public class RegisterController_24133072
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final IUserService_24133072 userService =
            new UserService_24133072();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/views/auth/register.jsp")
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

        String fullname =
                request.getParameter("fullname");

        String phone =
                request.getParameter("phone");

        String email =
                request.getParameter("email");

        if (username == null
                || username.trim().isEmpty()
                || password == null
                || password.trim().isEmpty()
                || fullname == null
                || fullname.trim().isEmpty()
                || email == null
                || email.trim().isEmpty()) {

            request.setAttribute(
                    "error",
                    "Vui lòng nhập đầy đủ thông tin.");

            request.getRequestDispatcher(
                    "/views/auth/register.jsp")
                    .forward(request, response);

            return;
        }

        username = username.trim();
        password = password.trim();
        fullname = fullname.trim();
        email = email.trim();

        if (userService.findByUsername(username) != null) {

            request.setAttribute(
                    "error",
                    "Tên đăng nhập đã tồn tại.");

            request.getRequestDispatcher(
                    "/views/auth/register.jsp")
                    .forward(request, response);

            return;
        }

        if (userService.findByEmail(email) != null) {

            request.setAttribute(
                    "error",
                    "Email đã được sử dụng.");

            request.getRequestDispatcher(
                    "/views/auth/register.jsp")
                    .forward(request, response);

            return;
        }

        String otp =
                OTPUtil_24133072.generateOTP();

        boolean emailSent =
                EmailUtil_24133072.sendOTP(
                        email,
                        otp);

        if (!emailSent) {

            request.setAttribute(
                    "error",
                    "Không thể gửi OTP. Vui lòng kiểm tra cấu hình email.");

            request.getRequestDispatcher(
                    "/views/auth/register.jsp")
                    .forward(request, response);

            return;
        }

        User_24133072 user =
                new User_24133072();

        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setAdmin(false);
        user.setActive(false);
        user.setImages(null);

        boolean registered =
                userService.register(user);

        if (!registered) {

            request.setAttribute(
                    "error",
                    "Không thể tạo tài khoản.");

            request.getRequestDispatcher(
                    "/views/auth/register.jsp")
                    .forward(request, response);

            return;
        }

        HttpSession session =
                request.getSession();

        session.setAttribute(
                "registerUsername",
                username);

        session.setAttribute(
                "registerOTP",
                otp);

        response.sendRedirect(
                request.getContextPath()
                + "/verify-otp");
    }
}