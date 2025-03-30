package com.example.springboot_crud_postgres.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.example.springboot_crud_postgres.Service.ProductService;
import com.example.springboot_crud_postgres.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        log.info("Received request to get all products");
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        log.info("Received request to get product with id: {}", id);
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        log.info("Received request to create new product: {}", product);
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        log.info("Received request to update product with id: {}", id);
        return ResponseEntity.ok(productService.updateProduct(id, productDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        log.info("Received request to delete product with id: {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
