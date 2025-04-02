package com.example.springboot_crud_postgres;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.springboot_crud_postgres.Repository.ProductRepository;
import com.example.springboot_crud_postgres.Service.ProductServiceImpl;
import com.example.springboot_crud_postgres.model.Product;

public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository; // Mocking ProductRepository

    @InjectMocks
    private ProductServiceImpl productService; // Injecting the mock into ProductServiceImpl

    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setDescription("Description 1");
        product1.setPrice(100.0);

        product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setDescription("Description 2");
        product2.setPrice(200.0);
    }

    @Test
    public void testGetAllProducts() {
        // Preparing the mock behavior of productRepository.findAll()
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        
        when(productRepository.findAll()).thenReturn(productList); // Mocking the repository

        // Calling the service method
        List<Product> result = productService.getAllProducts();

        // Verifying the result
        assertNotNull(result, "Product list should not be null");
        assertEquals(2, result.size(), "Product list should contain 2 products");
        assertEquals("Product 1", result.get(0).getName(), "First product name should be 'Product 1'");
        assertEquals("Product 2", result.get(1).getName(), "Second product name should be 'Product 2'");
    }

}
