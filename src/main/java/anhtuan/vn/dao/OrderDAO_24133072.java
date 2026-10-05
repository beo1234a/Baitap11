package anhtuan.vn.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;

import anhtuan.vn.config.JPAConfig_24133072;
import anhtuan.vn.entity.OrderDetail_24133072;
import anhtuan.vn.entity.Order_24133072;
import anhtuan.vn.entity.User_24133072;
import anhtuan.vn.entity.Video_24133072;
import anhtuan.vn.model.CartItem_24133072;
import anhtuan.vn.util.OrderStatus_24133072;

public class OrderDAO_24133072 implements IOrderDAO_24133072 {

    @Override
    public Order_24133072 createCodOrder(
            String username,
            String receiverName,
            String phone,
            String address,
            Map<String, CartItem_24133072> cart) {

        if (cart == null || cart.isEmpty()) {
            throw new IllegalStateException("Giỏ hàng đang trống.");
        }

        EntityManager em = JPAConfig_24133072.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            User_24133072 user = em.find(User_24133072.class, username);
            if (user == null) {
                throw new IllegalStateException("Không tìm thấy tài khoản đặt hàng.");
            }

            Order_24133072 order = new Order_24133072();
            order.setUser(user);
            order.setOrderDate(LocalDateTime.now());
            order.setReceiverName(receiverName);
            order.setPhone(phone);
            order.setAddress(address);
            order.setPaymentMethod("COD");
            order.setStatus(OrderStatus_24133072.NEW);

            List<OrderDetail_24133072> details = new ArrayList<>();
            BigDecimal total = BigDecimal.ZERO;

            for (Map.Entry<String, CartItem_24133072> entry : cart.entrySet()) {
                CartItem_24133072 cartItem = entry.getValue();
                int requestedQuantity = cartItem == null ? 0 : cartItem.getQuantity();

                if (requestedQuantity < 1) {
                    throw new IllegalStateException("Số lượng sản phẩm không hợp lệ.");
                }

                Video_24133072 video = em.find(
                        Video_24133072.class,
                        entry.getKey(),
                        LockModeType.PESSIMISTIC_WRITE);

                if (video == null || !Boolean.TRUE.equals(video.getActive())) {
                    throw new IllegalStateException("Sản phẩm " + entry.getKey() + " không còn khả dụng.");
                }

                int stock = video.getQuantity() == null ? 0 : video.getQuantity();
                if (requestedQuantity > stock) {
                    throw new IllegalStateException(
                            "Sản phẩm " + video.getTitle()
                            + " chỉ còn " + stock + " sản phẩm trong kho.");
                }

                BigDecimal price = video.getPrice() == null
                        ? BigDecimal.ZERO
                        : video.getPrice();

                video.setQuantity(stock - requestedQuantity);

                OrderDetail_24133072 detail = new OrderDetail_24133072();
                detail.setOrder(order);
                detail.setVideo(video);
                detail.setQuantity(requestedQuantity);
                detail.setUnitPrice(price);
                details.add(detail);

                total = total.add(
                        price.multiply(BigDecimal.valueOf(requestedQuantity)));
            }

            order.setTotalAmount(total);
            order.setDetails(details);

            em.persist(order);

            transaction.commit();
            return order;

        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Order_24133072> findByUser(
            String username,
            String status) {

        EntityManager em = JPAConfig_24133072.getEntityManager();

        try {
            String jpql =
                    "SELECT DISTINCT o "
                    + "FROM Order_24133072 o "
                    + "LEFT JOIN FETCH o.details d "
                    + "LEFT JOIN FETCH d.video "
                    + "WHERE o.user.username = :username ";

            if (status != null && !status.isBlank()) {
                jpql += "AND o.status = :status ";
            }

            jpql += "ORDER BY o.orderDate DESC, o.orderId DESC";

            javax.persistence.TypedQuery<Order_24133072> query =
                    em.createQuery(jpql, Order_24133072.class)
                    .setParameter("username", username);

            if (status != null && !status.isBlank()) {
                query.setParameter("status", status);
            }

            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
