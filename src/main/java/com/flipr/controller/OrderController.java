package com.flipr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flipr.model.Order;
import com.flipr.model.ShippingDetail;
import com.flipr.model.User;
import com.flipr.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeorder")
    public Order placeOrder(@RequestParam Long cartId, @RequestBody ShippingDetail shippingDetail, @RequestParam Long customerId) {
        User customer = new User(); 
        customer.setId(customerId); 
        
        return orderService.placeOrder(cartId, shippingDetail, customer);
    }

    @GetMapping("/getallorders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/customer/{customerId}")
    public List<Order> getOrdersByCustomerId(@PathVariable Long customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }
}
