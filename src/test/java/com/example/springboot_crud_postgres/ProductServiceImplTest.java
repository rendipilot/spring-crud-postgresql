package com.example.springboot_crud_postgres;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.springboot_crud_postgres.Repository.ProductRepository;
import com.example.springboot_crud_postgres.Service.ProductServiceImpl;
import com.example.springboot_crud_postgres.model.Product;

@SpringBootTest
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
    public void testGetAllProducts_WhenDatabaseFails_ShouldUseFallback() {
        doThrow(new RuntimeException("Database error")).when(productRepository).findAll();
        // Calling the service method and expecting it to fall back
        List<Product> result = productService.getAllProducts();

        // Verifying that the fallback method is called and the result is a new empty list
        assertEquals(0, result.size());
    }

    @Test
    public void testGetAllProducts_Success() {
        // Given: Ketika repository mengembalikan daftar produk
        List<Product> mockProducts = Arrays.asList(product1, product2);  // Gunakan produk yang sudah ada di setUp  
        when(productService.getAllProducts()).thenReturn(mockProducts);

        // When: Memanggil metode getAllProducts
        List<Product> result = productService.getAllProducts();

        // Then: Memastikan hasilnya sesuai dengan yang diharapkan
        assertEquals(2, result.size());  // Memastikan jumlah produk yang dikembalikan
        assertEquals("Product 1", result.get(0).getName());  // Memastikan produk pertama adalah "Product 1"
    }
}
