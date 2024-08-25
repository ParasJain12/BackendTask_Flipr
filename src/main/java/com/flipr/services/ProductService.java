package com.flipr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flipr.model.Product;
import com.flipr.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product addProduct(Product product) {
		if (product.getPrice() <= 0) {
			throw new IllegalArgumentException("Price must be a positive number");
		}
		return productRepository.save(product);
	}

	public Product updateProduct(Long productId, Product product) {
		Optional<Product> existingProduct = productRepository.findById(productId);
		if (existingProduct.isPresent()) {
			Product updatedProduct = existingProduct.get();
			updatedProduct.setName(product.getName() != null ? product.getName() : updatedProduct.getName());
			updatedProduct.setDescription(
					product.getDescription() != null ? product.getDescription() : updatedProduct.getDescription());
			updatedProduct.setPrice(product.getPrice() != null ? product.getPrice() : updatedProduct.getPrice());
			updatedProduct
					.setCategory(product.getCategory() != null ? product.getCategory() : updatedProduct.getCategory());
			return productRepository.save(updatedProduct);
		} else {
			throw new IllegalArgumentException("Product not found");
		}
	}

	public void deleteProduct(Long productId) {
		Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent()) {
			productRepository.delete(product.get());
		} else {
			throw new IllegalArgumentException("Product not found");
		}
	}

}
