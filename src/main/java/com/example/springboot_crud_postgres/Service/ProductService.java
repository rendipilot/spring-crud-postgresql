package com.example.springboot_crud_postgres.Service;

import java.util.List;
import com.example.springboot_crud_postgres.model.Product;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
