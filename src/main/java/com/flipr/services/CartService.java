package com.flipr.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flipr.model.Cart;
import com.flipr.model.CartItem;
import com.flipr.model.Product;
import com.flipr.repository.CartRepository;
import com.flipr.repository.ProductRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;

	public Cart addProductToCart(Long cartId, Long productId, int quantity) {
		Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent() && quantity > 0) {
			Cart cart = cartRepository.findById(cartId).orElse(new Cart());
			Optional<CartItem> existingItem = cart.getItems().stream()
					.filter(item -> item.getProduct().getId().equals(productId)).findFirst();

			if (existingItem.isPresent()) {
				existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
			} else {
				CartItem cartItem = new CartItem();
				cartItem.setProduct(product.get());
				cartItem.setQuantity(quantity);
				cart.getItems().add(cartItem);
			}
			return cartRepository.save(cart);
		} else {
			throw new IllegalArgumentException("Invalid product ID or quantity");
		}
	}

	public Cart updateCartItem(Long cartId, Long productId, int quantity) {
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException("Cart not found"));

		Optional<CartItem> existingItem = cart.getItems().stream()
				.filter(item -> item.getProduct().getId().equals(productId)).findFirst();

		if (existingItem.isPresent()) {
			if (quantity == 0) {
				cart.getItems().remove(existingItem.get());
			} else {
				existingItem.get().setQuantity(quantity);
			}
			return cartRepository.save(cart);
		} else {
			throw new IllegalArgumentException("Product not found in cart");
		}
	}

	public Cart deleteProductFromCart(Long cartId, Long productId) {
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException("Cart not found"));

		cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
		return cartRepository.save(cart);
	}

	public Cart getCart(Long cartId) {
		return cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException("Cart not found"));
	}
}
