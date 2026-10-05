<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="vi">
<head><title>Lịch sử đặt hàng</title></head>
<body>
<div class="surface">
    <div class="page-header"><div><h1>Lịch sử đặt hàng</h1><div class="page-subtitle">Lọc đơn theo trạng thái hiện tại trong database.</div></div></div>

    <div class="status-tabs">
        <a class="${empty selectedStatus ? 'active' : ''}" href="${pageContext.request.contextPath}/orders">Tất cả</a>
        <c:forEach var="entry" items="${statusLabels}">
            <a class="${selectedStatus == entry.key ? 'active' : ''}" href="${pageContext.request.contextPath}/orders?status=${entry.key}">${entry.value}</a>
        </c:forEach>
    </div>

    <c:choose>
        <c:when test="${empty orders}"><div class="empty-state">Không có đơn hàng ở trạng thái này.</div></c:when>
        <c:otherwise>
            <div class="order-list">
                <c:forEach var="order" items="${orders}">
                    <div class="order-card">
                        <div class="order-card-head">
                            <div><strong>Đơn #${order.orderId}</strong><div class="muted-text">${order.orderDateDisplay}</div></div>
                            <span class="order-status status-${order.status}">${statusLabels[order.status]}</span>
                        </div>
                        <div class="order-info-grid">
                            <div><span>Người nhận</span><strong>${order.receiverName}</strong></div>
                            <div><span>SĐT</span><strong>${order.phone}</strong></div>
                            <div><span>Thanh toán</span><strong>${order.paymentMethod}</strong></div>
                            <div><span>Tổng tiền</span><strong><fmt:formatNumber value="${order.totalAmount}" type="number" groupingUsed="true"/> đ</strong></div>
                        </div>
                        <div class="order-address"><strong>Địa chỉ:</strong> ${order.address}</div>
                        <div class="order-items">
                            <c:forEach var="detail" items="${order.details}">
                                <div class="order-item-line">
                                    <span>${detail.video.title} × ${detail.quantity}</span>
                                    <strong><fmt:formatNumber value="${detail.subtotal}" type="number" groupingUsed="true"/> đ</strong>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
