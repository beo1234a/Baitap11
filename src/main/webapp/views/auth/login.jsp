<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <title>Đăng nhập</title>
</head>

<body>

<div class="auth-wrapper">

    <div class="auth-card">

        <h1 class="auth-title">
            Đăng nhập
        </h1>

        <p class="auth-desc">
            Đăng nhập để sử dụng hệ thống Video
        </p>

        <c:if test="${not empty message}">
            <div class="alert alert-success">
                ${message}
            </div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                ${error}
            </div>
        </c:if>

        <form method="post"
              action="${pageContext.request.contextPath}/login">

            <div class="form-group">

                <label class="form-label">
                    Tên đăng nhập
                </label>

                <input class="form-control"
                       type="text"
                       name="username"
                       placeholder="Nhập tên đăng nhập"
                       required>

            </div>

            <div class="form-group">

                <label class="form-label">
                    Mật khẩu
                </label>

                <input class="form-control"
                       type="password"
                       name="password"
                       placeholder="Nhập mật khẩu"
                       required>

            </div>

            <button class="btn btn-primary"
                    style="width:100%;"
                    type="submit">

                Đăng nhập

            </button>

        </form>

        <div class="auth-link">

            Chưa có tài khoản?

            <a href="${pageContext.request.contextPath}/register">
                Đăng ký ngay
            </a>

        </div>

    </div>

</div>

</body>

</html>