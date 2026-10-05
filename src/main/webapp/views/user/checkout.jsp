<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="vi">
<head><title>Thanh toán COD</title></head>
<body>
<div class="checkout-grid">
    <div class="surface">
        <div class="page-header"><div><h1>Thông tin giao hàng</h1><div class="page-subtitle">Phương thức thanh toán: COD - thanh toán khi nhận hàng.</div></div></div>
        <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
        <form method="post" action="${pageContext.request.contextPath}/checkout">
            <div class="form-group"><label class="form-label">Người nhận</label><input class="form-control" type="text" name="receiverName" value="${receiverName}" maxlength="100" required></div>
            <div class="form-group"><label class="form-label">Số điện thoại</label><input class="form-control" type="text" name="phone" value="${phone}" maxlength="20" required></div>
            <div class="form-group"><label class="form-label">Địa chỉ giao hàng</label><textarea class="form-control" name="address" maxlength="500" required>${address}</textarea></div>
            <div class="payment-box"><strong>COD</strong><span>Thanh toán tiền mặt khi nhận hàng</span></div>
            <div class="form-actions"><a class="btn btn-secondary" href="${pageContext.request.contextPath}/cart">Quay lại giỏ hàng</a><button class="btn btn-success" type="submit">Đặt hàng COD</button></div>
        </form>
    </div>
    <div class="surface order-summary-card">
        <h2>Đơn hàng</h2>
        <c:forEach var="item" items="${cartItems}">
            <div class="summary-line"><span>${item.video.title} × ${item.quantity}</span><strong><fmt:formatNumber value="${item.subtotal}" type="number" groupingUsed="true"/> đ</strong></div>
        </c:forEach>
        <div class="summary-total"><span>Tổng cộng</span><strong><fmt:formatNumber value="${cartTotal}" type="number" groupingUsed="true"/> đ</strong></div>
    </div>
</div>
</body>
</html>
