package com.example.productservice.service.impl;

import com.example.productservice.data.entities.Product;
import com.example.productservice.data.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @Test
    void create() {
        Product product = new Product("1234", "product1", "des", 1.0, 100, "1234");
        when(productRepository.save(product)).thenReturn(product);
        Product createdProduct = productServiceImpl.create(product);
        assertEquals(product, createdProduct);
    }

    @Test
    void getAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(new Product("1234", "product1", "des", 1.0, 100, "1234")));
        List<Product> products = productServiceImpl.getAllProducts();
        assertEquals(1, products.size());
    }
}