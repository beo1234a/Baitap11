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
        Admin · <sitemesh:write property="title"/>
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
                    AD
                </div>

                <div>

                    <div class="brand-title">
                        ADMIN MANAGEMENT
                    </div>

                    <div class="brand-subtitle">
                        Video Management System
                    </div>

                </div>

            </div>

            <div class="user-box">

                <strong>
                    ${sessionScope.account.fullname}
                </strong>

                Administrator · 24133072

            </div>

        </div>

        <nav class="main-nav">

            <a href="${pageContext.request.contextPath}/home">
                Trang Chủ
            </a>

            <a href="${pageContext.request.contextPath}/home">
                Sản phẩm
            </a>

            <a href="${pageContext.request.contextPath}/admin/home">
                Trang quản trị
            </a>

            <a href="${pageContext.request.contextPath}/admin/videos">
                Quản lý Video
            </a>

            <a href="${pageContext.request.contextPath}/logout">
                Đăng xuất
            </a>

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