<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="vi">
<head><title>Giỏ hàng</title></head>
<body>
<div class="surface">
    <div class="page-header">
        <div>
            <h1>Giỏ hàng của bạn</h1>
            <div class="page-subtitle">Thêm, xóa và thay đổi số lượng trong giới hạn tồn kho.</div>
        </div>
        <a class="btn btn-light" href="${pageContext.request.contextPath}/home">← Tiếp tục mua hàng</a>
    </div>

    <c:if test="${not empty message}"><div class="alert alert-success">${message}</div></c:if>
    <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>

    <c:choose>
        <c:when test="${empty cartItems}">
            <div class="empty-state">Giỏ hàng đang trống.</div>
        </c:when>
        <c:otherwise>
            <div class="table-wrapper">
                <table class="data-table">
                    <thead><tr><th>Sản phẩm</th><th>Đơn giá</th><th>Tồn kho</th><th>Số lượng</th><th>Thành tiền</th><th>Thao tác</th></tr></thead>
                    <tbody>
                    <c:forEach var="item" items="${cartItems}">
                        <tr>
                            <td><strong>${item.video.title}</strong><br><span class="muted-text">${item.video.videoId}</span></td>
                            <td><fmt:formatNumber value="${item.video.price}" type="number" groupingUsed="true"/> đ</td>
                            <td>${item.video.quantity}</td>
                            <td>
                                <form class="inline-form" method="post" action="${pageContext.request.contextPath}/cart/update">
                                    <input type="hidden" name="videoId" value="${item.video.videoId}">
                                    <input class="qty-input" type="number" name="quantity" min="1" max="${item.video.quantity}" value="${item.quantity}" required>
                                    <button class="btn btn-primary" type="submit">Cập nhật</button>
                                </form>
                            </td>
                            <td><strong><fmt:formatNumber value="${item.subtotal}" type="number" groupingUsed="true"/> đ</strong></td>
                            <td>
                                <form method="post" action="${pageContext.request.contextPath}/cart/remove">
                                    <input type="hidden" name="videoId" value="${item.video.videoId}">
                                    <button class="btn btn-danger" type="submit">Xóa</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="cart-summary">
                <div><span>Tổng thanh toán</span><strong><fmt:formatNumber value="${cartTotal}" type="number" groupingUsed="true"/> đ</strong></div>
                <div class="form-actions">
                    <form method="post" action="${pageContext.request.contextPath}/cart/clear">
                        <button class="btn btn-secondary" type="submit">Xóa toàn bộ</button>
                    </form>
                    <a class="btn btn-success" href="${pageContext.request.contextPath}/checkout">Thanh toán COD</a>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
