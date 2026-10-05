package anhtuan.vn.service;

import java.util.List;
import java.util.Map;

import anhtuan.vn.entity.Order_24133072;
import anhtuan.vn.model.CartItem_24133072;

public interface IOrderService_24133072 {

    Order_24133072 createCodOrder(
            String username,
            String receiverName,
            String phone,
            String address,
            Map<String, CartItem_24133072> cart);

    List<Order_24133072> findByUser(
            String username,
            String status);
}
