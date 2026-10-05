<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <title>Quản lý Video</title>
</head>

<body>

<div class="surface">

    <div class="page-header">

        <div>

            <h1>Quản lý Video</h1>

            <div class="page-subtitle">
                Quản lý danh sách, cập nhật và phân trang Video
            </div>

        </div>

        <a class="btn btn-success"
           href="${pageContext.request.contextPath}/admin/videos/add">

            + Thêm Video

        </a>

    </div>

    <div class="summary-bar">

        <div class="summary-chip">
            Tổng Video: <strong>${totalVideos}</strong>
        </div>

        <div class="summary-chip">
            Trang <strong>${currentPage}/${totalPages}</strong>
        </div>

        <div class="summary-chip">
            <strong>6</strong> video/trang
        </div>

    </div>

    <div class="table-wrapper">

        <table class="data-table">

            <thead>

                <tr>
                    <th>Mã Video</th>
                    <th>Poster</th>
                    <th>Tiêu đề</th>
                    <th>Category</th>
                    <th>Views</th>
                    <th>Giá</th>
                    <th>Tồn kho</th>
                    <th>Trạng thái</th>
                    <th>Thao tác</th>
                </tr>

            </thead>

            <tbody>

                <c:forEach var="video"
                           items="${videos}">

                    <tr>

                        <td>
                            <strong>${video.videoId}</strong>
                        </td>

                        <td>

                            <c:choose>

                                <c:when test="${not empty video.poster}">

                                    <img class="table-poster"
                                         src="${pageContext.request.contextPath}/uploads/posters/${video.poster}"
                                         alt="${video.title}">

                                </c:when>

                                <c:otherwise>
                                    Chưa có ảnh
                                </c:otherwise>

                            </c:choose>

                        </td>

                        <td>
                            ${video.title}
                        </td>

                        <td>
                            <span class="badge badge-blue">
                                ${video.category.categoryname}
                            </span>
                        </td>

                        <td>
                            ${video.views}
                        </td>

                        <td>
                            <fmt:formatNumber value="${video.price}" type="number" groupingUsed="true"/> đ
                        </td>

                        <td>
                            ${video.quantity}
                        </td>

                        <td>

                            <c:choose>

                                <c:when test="${video.active}">

                                    <span class="badge badge-success">
                                        Hoạt động
                                    </span>

                                </c:when>

                                <c:otherwise>

                                    <span class="badge badge-danger">
                                        Đã ẩn
                                    </span>

                                </c:otherwise>

                            </c:choose>

                        </td>

                        <td>

                            <div class="action-group">

                                <a class="btn btn-warning"
                                   href="${pageContext.request.contextPath}/admin/videos/edit?id=${video.videoId}">
                                    Sửa
                                </a>

                                <a class="btn btn-danger"
                                   href="${pageContext.request.contextPath}/admin/videos/delete?id=${video.videoId}"
                                   onclick="return confirm('Bạn có chắc muốn xóa Video này?');">
                                    Xóa
                                </a>

                            </div>

                        </td>

                    </tr>

                </c:forEach>

            </tbody>

        </table>

    </div>

    <div class="pagination">

        <c:if test="${currentPage > 1}">

            <a href="${pageContext.request.contextPath}/admin/videos?page=${currentPage - 1}">
                ‹
            </a>

        </c:if>

        <c:forEach begin="1"
                   end="${totalPages}"
                   var="i">

            <a class="${i == currentPage ? 'active' : ''}"
               href="${pageContext.request.contextPath}/admin/videos?page=${i}">
                ${i}
            </a>

        </c:forEach>

        <c:if test="${currentPage < totalPages}">

            <a href="${pageContext.request.contextPath}/admin/videos?page=${currentPage + 1}">
                ›
            </a>

        </c:if>

    </div>

</div>

</body>

</html>