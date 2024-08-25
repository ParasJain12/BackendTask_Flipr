package com.flipr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flipr.model.Cart;
import com.flipr.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public Cart addProductToCart(@RequestParam Long cartId, @RequestParam Long productId, @RequestParam int quantity) {
        return cartService.addProductToCart(cartId, productId, quantity);
    }

    @PutMapping("/update")
    public Cart updateCartItem(@RequestParam Long cartId, @RequestParam Long productId, @RequestParam int quantity) {
        return cartService.updateCartItem(cartId, productId, quantity);
    }

    @DeleteMapping("/delete")
    public Cart deleteProductFromCart(@RequestParam Long cartId, @RequestParam Long productId) {
        return cartService.deleteProductFromCart(cartId, productId);
    }

    @GetMapping
    public Cart getCart(@RequestParam Long cartId) {
        return cartService.getCart(cartId);
    }
}
