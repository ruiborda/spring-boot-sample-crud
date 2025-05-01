package com.example.sample.service;

import com.example.sample.dto.GETApiV1FindAllProductsResponseDTO;
import com.example.sample.dto.GETApiV1FindProductByIdResponseDTO;
import com.example.sample.dto.POSTApiV1CreateProductRequestDTO;
import com.example.sample.dto.POSTApiV1CreateProductResponseDTO;
import com.example.sample.dto.PUTApiV1UpdateProductRequestDTO;

import java.util.UUID;

public interface ProductService {
    
    GETApiV1FindAllProductsResponseDTO findAll();
    
    GETApiV1FindProductByIdResponseDTO findById(UUID id);
    
    POSTApiV1CreateProductResponseDTO create(POSTApiV1CreateProductRequestDTO requestDTO);
    
    GETApiV1FindProductByIdResponseDTO update(UUID id, PUTApiV1UpdateProductRequestDTO requestDTO);
    
    void delete(UUID id);
}