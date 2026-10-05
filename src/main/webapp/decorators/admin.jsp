<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh" uri="http://www.sitemesh.org/decorator"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>
        Admin -
        <sitemesh:write property="title"/>
    </title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #eef1f5;
            color: #222;
        }

        .header {
            background: #243447;
            color: white;
            padding: 18px 40px;
        }

        .header-title {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 14px;
        }

        .menu {
            display: flex;
            gap: 12px;
            flex-wrap: wrap;
        }

        .menu a {
            color: white;
            text-decoration: none;
            padding: 9px 16px;
            border-radius: 6px;
            background: rgba(255, 255, 255, 0.12);
        }

        .menu a:hover {
            background: rgba(255, 255, 255, 0.25);
        }

        .container {
            width: 94%;
            max-width: 1300px;
            margin: 25px auto;
            min-height: 520px;
            background: white;
            padding: 25px;
            border-radius: 10px;
        }

        .footer {
            background: #243447;
            color: white;
            text-align: center;
            padding: 18px;
            margin-top: 25px;
        }
    </style>

    <sitemesh:write property="head"/>

</head>

<body>

<header class="header">

    <div class="header-title">
        ADMIN MANAGEMENT
    </div>

    <nav class="menu">

        <a href="${pageContext.request.contextPath}/home">
            Trang Chủ
        </a>

        <a href="${pageContext.request.contextPath}/home">
            Sản phẩm
        </a>

        <a href="${pageContext.request.contextPath}/login">
            Đăng nhập
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

</header>

<main class="container">

    <sitemesh:write property="body"/>

</main>

<footer class="footer">

    Nguyễn Anh Tuấn -
    MSSV: 24133072 -
    Mã đề: 03

</footer>

</body>
</html>