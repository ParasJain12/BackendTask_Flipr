package com.flipr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flipr.model.Product;
import com.flipr.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addproduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        try {
            Product savedProduct = productService.addProduct(product);
            return ResponseEntity.ok("Product added successfully! Product ID: " + savedProduct.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/updateproduct/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        try {
            productService.updateProduct(productId, product);
            return ResponseEntity.ok("Product updated successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteproduct/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok("Product deleted successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return ResponseEntity.ok("No products found");
        }
        return ResponseEntity.ok(products);
    }
}