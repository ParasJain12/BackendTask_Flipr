package com.flipr.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flipr.model.Order;
import com.flipr.model.Cart;
import com.flipr.model.CartItem;
import com.flipr.model.OrderItem;
import com.flipr.model.ShippingDetail;
import com.flipr.model.User;
import com.flipr.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    public Order placeOrder(Long cartId, ShippingDetail shippingDetail, User customer) {
        Cart cart = cartService.getCart(cartId);

        if (cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setCustomer(customer);
        order.setOrderDate(new Date());
        order.setShippingDetail(shippingDetail);
        order.setStatus("Placed");

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
            order.getItems().add(orderItem);
        }
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}
