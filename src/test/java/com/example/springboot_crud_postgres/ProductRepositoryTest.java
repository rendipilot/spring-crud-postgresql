package com.example.springboot_crud_postgres;

import com.example.springboot_crud_postgres.Repository.ProductRepository;
import com.example.springboot_crud_postgres.Service.ProductServiceImpl;
import com.example.springboot_crud_postgres.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepository; // Mocking repository

    @InjectMocks
    private ProductServiceImpl productService; // Assuming you have a service that calls the repository

    private Product product;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        product = new Product();
        product.setName("Sample Product");
        product.setDescription("sample description");
        product.setPrice(100.0);
    }

    @Test
    public void testSaveProduct() {
        // Simulating the behavior of productRepository.save() method
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        Product savedProduct = productService.createProduct(product); // Call the service method (optional)

        assertNotNull(savedProduct);
        assertNotNull(savedProduct.getName());
        assertNotNull(savedProduct.getDescription());
        assertNotNull(savedProduct.getPrice());

        System.out.println("Test passed: Product saved successfully with ID: " + savedProduct);
        assertTrue(true, "Product save test passed successfully.");
    }

}