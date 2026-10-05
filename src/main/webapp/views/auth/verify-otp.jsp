<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <title>Xác nhận OTP</title>
</head>

<body>

<div class="auth-wrapper">

    <div class="auth-card">

        <h1 class="auth-title">
            Xác nhận OTP
        </h1>

        <p class="auth-desc">
            Mã gồm 6 chữ số đã được gửi tới email đăng ký
        </p>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                ${error}
            </div>
        </c:if>

        <form method="post"
              action="${pageContext.request.contextPath}/verify-otp">

            <div class="form-group">

                <label class="form-label">
                    Mã OTP
                </label>

                <input class="form-control"
                       type="text"
                       name="otp"
                       maxlength="6"
                       pattern="[0-9]{6}"
                       placeholder="Nhập 6 chữ số"
                       style="text-align:center;
                              font-size:22px;
                              letter-spacing:8px;"
                       required>

            </div>

            <button class="btn btn-primary"
                    style="width:100%;"
                    type="submit">

                Xác nhận kích hoạt

            </button>

        </form>

    </div>

</div>

</body>

</html>