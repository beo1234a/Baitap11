<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <title>Trang chủ</title>
</head>

<body>

<div class="surface">

    <div class="page-header">

        <div>

            <h1>Khám phá Video</h1>

            <div class="page-subtitle">
                Danh sách Video được phân loại theo Category
            </div>

        </div>

    </div>

    <c:if test="${not empty categories}">

        <div class="category-tabs">

            <c:forEach var="category"
                       items="${categories}">

                <a class="${selectedCategory.categoryId == category.categoryId ? 'active' : ''}"
                   href="${pageContext.request.contextPath}/home?categoryId=${category.categoryId}&page=1">

                    ${category.categoryname}
                    (${categoryCounts[category.categoryId]})

                </a>

            </c:forEach>

        </div>

        <div class="section-title">

            <h2>
                ${selectedCategory.categoryname}
            </h2>

            <span class="badge badge-blue">
                ${totalVideos} Video
            </span>

        </div>

        <c:choose>

            <c:when test="${empty videos}">

                <div class="empty-state">
                    Category này chưa có Video.
                </div>

            </c:when>

            <c:otherwise>

                <div class="video-grid">

                    <c:forEach var="video"
                               items="${videos}">

                        <div class="video-card">

                            <div class="video-poster-wrap">

                                <c:choose>

                                    <c:when test="${not empty video.poster}">

                                        <img class="video-poster"
                                             src="${pageContext.request.contextPath}/uploads/posters/${video.poster}"
                                             alt="${video.title}">

                                    </c:when>

                                    <c:otherwise>

                                        <div class="video-no-poster">
                                            Chưa có poster
                                        </div>

                                    </c:otherwise>

                                </c:choose>

                            </div>

                            <div class="video-body">

                                <div class="video-title">
                                    ${video.title}
                                </div>

                                <div class="video-meta">

                                    <div>
                                        <strong>Mã:</strong>
                                        ${video.videoId}
                                    </div>

                                    <div>
                                        <strong>Category:</strong>
                                        ${video.category.categoryname}
                                    </div>

                                    <div>
                                        <strong>Views:</strong>
                                        ${video.views}
                                    </div>

                                </div>

                                <div class="video-stats">

                                    <span class="video-stat">
                                        Share ${shareCounts[video.videoId]}
                                    </span>

                                    <span class="video-stat">
                                        Like ${likeCounts[video.videoId]}
                                    </span>

                                </div>

                                <a class="btn btn-primary"
                                   href="${pageContext.request.contextPath}/video/detail?id=${video.videoId}">

                                    Xem chi tiết

                                </a>

                            </div>

                        </div>

                    </c:forEach>

                </div>

            </c:otherwise>

        </c:choose>

        <div class="pagination">

            <c:if test="${currentPage > 1}">

                <a href="${pageContext.request.contextPath}/home?categoryId=${selectedCategory.categoryId}&page=${currentPage - 1}">
                    ‹
                </a>

            </c:if>

            <c:forEach begin="1"
                       end="${totalPages}"
                       var="i">

                <a class="${i == currentPage ? 'active' : ''}"
                   href="${pageContext.request.contextPath}/home?categoryId=${selectedCategory.categoryId}&page=${i}">
                    ${i}
                </a>

            </c:forEach>

            <c:if test="${currentPage < totalPages}">

                <a href="${pageContext.request.contextPath}/home?categoryId=${selectedCategory.categoryId}&page=${currentPage + 1}">
                    ›
                </a>

            </c:if>

        </div>

        <div style="text-align:center;
                    color:#64748b;
                    margin-top:10px;">

            Trang ${currentPage}/${totalPages}
            · 3 video/trang

        </div>

    </c:if>

</div>

</body>

</html>