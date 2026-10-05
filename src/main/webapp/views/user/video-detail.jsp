<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <title>${video.title}</title>
</head>

<body>

<div class="surface detail-card">

    <div class="detail-grid">

        <div class="detail-poster-wrap">

            <c:choose>

                <c:when test="${not empty video.poster}">

                    <img class="detail-poster"
                         src="${pageContext.request.contextPath}/uploads/posters/${video.poster}"
                         alt="${video.title}">

                </c:when>

                <c:otherwise>

                    <div class="empty-state">
                        Chưa có poster
                    </div>

                </c:otherwise>

            </c:choose>

        </div>

        <div class="detail-info">

            <h1>
                ${video.title}
            </h1>

            <div class="detail-row">
                <strong>Mã video</strong>
                <span>${video.videoId}</span>
            </div>

            <div class="detail-row">
                <strong>Category name</strong>
                <span>${video.category.categoryname}</span>
            </div>

            <div class="detail-row">
                <strong>View</strong>
                <span>${video.views}</span>
            </div>

            <div class="video-stats"
                 style="margin-top:22px;">

                <span class="video-stat">
                    Share (${shareCount})
                </span>

                <span class="video-stat">
                    Like (${likeCount})
                </span>

            </div>

            <a class="btn btn-light"
               style="margin-top:18px;"
               href="${pageContext.request.contextPath}/home">

                ← Quay lại Trang Chủ

            </a>

        </div>

    </div>

    <div class="detail-description">

        <h3>Mô tả Video</h3>

        <c:choose>

            <c:when test="${not empty video.description}">
                ${video.description}
            </c:when>

            <c:otherwise>
                Video chưa có mô tả.
            </c:otherwise>

        </c:choose>

    </div>

</div>

</body>

</html>