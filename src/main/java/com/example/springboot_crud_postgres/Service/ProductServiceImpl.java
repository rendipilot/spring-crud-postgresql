package com.example.springboot_crud_postgres.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.springboot_crud_postgres.Repository.ProductRepository;
import com.example.springboot_crud_postgres.model.Product;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackGetAllProducts")
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll(); // Mengakses database
    }

    // Fallback method jika circuit breaker diaktifkan
    public List<Product> fallbackGetAllProducts(Throwable t) {
        log.error("Database tidak dapat diakses, menggunakan fallback method.", t);
        return new ArrayList<>(); // Mengembalikan daftar kosong atau data cadangan lainnya
    }

    @Override
    public Product getProductById(Long id) {
        log.info("Fetching product with id: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with id: {}", id);
                    return new RuntimeException("Product not found with id: " + id);
                });
    }

    @Override
    public Product createProduct(Product product) {
        log.info("Creating new product: {}", product);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        log.info("Updating product with id: {}", id);
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Deleting product with id: {}", id);
        Product product = getProductById(id);
        productRepository.delete(product);
    }

}
