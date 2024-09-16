package com.example.oms.service;

import com.example.oms.entity.Product;
import com.example.oms.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduct() {
        Product product = new Product();
        product.setName("Product A");
        product.setPrice(100.0);

        when(productRepository.save(product)).thenReturn(product);

        Product createdProduct = productService.saveProduct(product);

        assertNotNull(createdProduct);
        assertEquals("Product A", createdProduct.getName());
        assertEquals(100.0, createdProduct.getPrice());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void getProductById() {
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Product A");
        product.setPrice(100.0);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.getProductById(productId);

        assertTrue(foundProduct.isPresent());
        assertEquals("Product A", foundProduct.get().getName());
        assertEquals(100.0, foundProduct.get().getPrice());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void getAllProducts() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setName("Product A");
        product1.setPrice(100.0);
        Product product2 = new Product();
        product2.setName("Product B");
        product2.setPrice(200.0);
        products.add(product1);
        products.add(product2);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> foundProducts = productService.getAllProducts();

        assertEquals(2, foundProducts.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void updateProduct() {
        Long productId = 1L;
        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setName("Old Name");
        existingProduct.setPrice(10.0);

        Product updatedProductDetails = new Product();
        updatedProductDetails.setName("New Name");
        updatedProductDetails.setPrice(20.0);

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        Product updatedProduct = productService.updateProduct(productId, updatedProductDetails);

        assertNotNull(updatedProduct);
        assertEquals("New Name", updatedProduct.getName());
        assertEquals(20.0, updatedProduct.getPrice());
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    void deleteProduct() {
        Long productId = 1L;
        doNothing().when(productRepository).deleteById(productId);

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }
}