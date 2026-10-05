package anhtuan.vn.service;

import java.util.List;
import java.util.Map;

import anhtuan.vn.dao.IOrderDAO_24133072;
import anhtuan.vn.dao.OrderDAO_24133072;
import anhtuan.vn.entity.Order_24133072;
import anhtuan.vn.model.CartItem_24133072;

public class OrderService_24133072 implements IOrderService_24133072 {

    private final IOrderDAO_24133072 orderDAO = new OrderDAO_24133072();

    @Override
    public Order_24133072 createCodOrder(
            String username,
            String receiverName,
            String phone,
            String address,
            Map<String, CartItem_24133072> cart) {

        return orderDAO.createCodOrder(
                username,
                receiverName,
                phone,
                address,
                cart);
    }

    @Override
    public List<Order_24133072> findByUser(
            String username,
            String status) {

        return orderDAO.findByUser(username, status);
    }
}
