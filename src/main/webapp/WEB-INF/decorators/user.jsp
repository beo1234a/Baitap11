<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>
        <sitemesh:write property="title"/>
    </title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/app.css">

    <sitemesh:write property="head"/>

</head>

<body>

<header class="site-header">

    <div class="header-inner">

        <div class="header-top">

            <div class="brand-block">

                <div class="brand-logo">
                    VT
                </div>

                <div>

                    <div class="brand-title">
                        VIDEO WEBSITE
                    </div>

                    <div class="brand-subtitle">
                        Nguyễn Anh Tuấn · 24133072 · Đề 03
                    </div>

                </div>

            </div>

            <c:if test="${sessionScope.account != null}">

                <div class="user-box">

                    <strong>
                        ${sessionScope.account.fullname}
                    </strong>

                    Tài khoản người dùng

                </div>

            </c:if>

        </div>

        <nav class="main-nav">

            <a href="${pageContext.request.contextPath}/home">
                Trang Chủ
            </a>

            <a href="${pageContext.request.contextPath}/home">
                Sản phẩm
            </a>

            <c:choose>

                <c:when test="${sessionScope.account == null}">

                    <a href="${pageContext.request.contextPath}/login">
                        Đăng nhập
                    </a>

                    <a href="${pageContext.request.contextPath}/register">
                        Đăng ký
                    </a>

                </c:when>

                <c:otherwise>

                    <c:if test="${sessionScope.account.admin}">

                        <a href="${pageContext.request.contextPath}/admin/home">
                            Trang quản trị
                        </a>

                    </c:if>

                    <a href="${pageContext.request.contextPath}/logout">
                        Đăng xuất
                    </a>

                </c:otherwise>

            </c:choose>

        </nav>

    </div>

</header>

<main class="main-content">

    <sitemesh:write property="body"/>

</main>

<footer class="site-footer">

    Nguyễn Anh Tuấn · MSSV 24133072 · Mã đề 03

</footer>

</body>

</html>