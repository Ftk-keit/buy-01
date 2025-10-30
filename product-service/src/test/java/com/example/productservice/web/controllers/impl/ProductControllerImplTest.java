package com.example.productservice.web.controllers.impl;

import com.example.productservice.data.entities.Product;
import com.example.productservice.service.impl.ProductServiceImpl;
import com.example.productservice.web.dto.ProductDto;
import com.example.productservice.web.mapper.ProductMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductControllerImpl.class)
class ProductControllerImplTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    ProductServiceImpl productServiceImpl;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create() throws Exception{
        ProductDto product = ProductMapper.toDto(new Product("1234", "product1", "des", 1.0, 100, "1234"));
        when(productServiceImpl.create(ProductMapper.toEntity(product))).thenReturn(ProductMapper.toEntity(product));
        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());

    }

    @Test
    void getAll() throws Exception{
        List<Product> products = List.of(new Product("1234", "product1", "des", 1.0, 100, "1234"), new Product("1235", "product2", "des", 1.0, 100, "1235"));
        when(productServiceImpl.getAllProducts()).thenReturn(products);
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals(objectMapper.writeValueAsString(products), result.getResponse().getContentAsString()));
    }
}