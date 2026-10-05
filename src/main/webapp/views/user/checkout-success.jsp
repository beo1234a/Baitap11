<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="vi">
<head><title>Đặt hàng thành công</title></head>
<body>
<div class="surface success-panel">
    <div class="success-icon">✓</div>
    <h1>Đặt hàng thành công</h1>
    <p>Mã đơn hàng: <strong>#${order.orderId}</strong></p>
    <p>Phương thức: <strong>COD</strong> · Trạng thái: <strong>Đơn hàng mới</strong></p>
    <p>Tổng tiền: <strong><fmt:formatNumber value="${order.totalAmount}" type="number" groupingUsed="true"/> đ</strong></p>
    <div class="form-actions center-actions">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/orders">Xem lịch sử đặt hàng</a>
        <a class="btn btn-light" href="${pageContext.request.contextPath}/home">Tiếp tục mua hàng</a>
    </div>
</div>
</body>
</html>
