<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <title>Đăng ký</title>
</head>

<body>

<div class="auth-wrapper">

    <div class="auth-card"
         style="width:min(540px,100%);">

        <h1 class="auth-title">
            Tạo tài khoản
        </h1>

        <p class="auth-desc">
            Đăng ký và kích hoạt bằng mã OTP
        </p>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                ${error}
            </div>
        </c:if>

        <form method="post"
              action="${pageContext.request.contextPath}/register">

            <div class="form-group">

                <label class="form-label">
                    Tên đăng nhập
                </label>

                <input class="form-control"
                       type="text"
                       name="username"
                       required>

            </div>

            <div class="form-group">

                <label class="form-label">
                    Mật khẩu
                </label>

                <input class="form-control"
                       type="password"
                       name="password"
                       required>

            </div>

            <div class="form-group">

                <label class="form-label">
                    Họ tên
                </label>

                <input class="form-control"
                       type="text"
                       name="fullname"
                       required>

            </div>

            <div class="form-group">

                <label class="form-label">
                    Số điện thoại
                </label>

                <input class="form-control"
                       type="text"
                       name="phone">

            </div>

            <div class="form-group">

                <label class="form-label">
                    Email
                </label>

                <input class="form-control"
                       type="email"
                       name="email"
                       required>

            </div>

            <button class="btn btn-primary"
                    style="width:100%;"
                    type="submit">

                Đăng ký và nhận OTP

            </button>

        </form>

        <div class="auth-link">

            Đã có tài khoản?

            <a href="${pageContext.request.contextPath}/login">
                Đăng nhập
            </a>

        </div>

    </div>

</div>

</body>

</html>