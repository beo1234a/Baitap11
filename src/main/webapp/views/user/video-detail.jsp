<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

            <div class="detail-row">
                <strong>Giá</strong>
                <span class="product-price"><fmt:formatNumber value="${video.price}" type="number" groupingUsed="true"/> đ</span>
            </div>

            <div class="detail-row">
                <strong>Tồn kho</strong>
                <span>${video.quantity}</span>
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

            <div class="detail-actions">
                <c:if test="${video.quantity > 0}">
                    <form class="inline-form" method="post" action="${pageContext.request.contextPath}/cart/add">
                        <input type="hidden" name="videoId" value="${video.videoId}">
                        <input class="qty-input" type="number" name="quantity" min="1" max="${video.quantity}" value="1" required>
                        <button class="btn btn-success" type="submit">Thêm vào giỏ</button>
                    </form>
                </c:if>
                <c:if test="${video.quantity <= 0}"><span class="badge badge-danger">Hết hàng</span></c:if>
                <a class="btn btn-light" href="${pageContext.request.contextPath}/home">← Quay lại Trang Chủ</a>
            </div>

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