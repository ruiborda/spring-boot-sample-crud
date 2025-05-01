package com.example.sample.service;

import com.example.sample.dto.GETApiV1FindAllProductsResponseDTO;
import com.example.sample.dto.GETApiV1FindProductByIdResponseDTO;
import com.example.sample.dto.POSTApiV1CreateProductRequestDTO;
import com.example.sample.dto.POSTApiV1CreateProductResponseDTO;
import com.example.sample.dto.PUTApiV1UpdateProductRequestDTO;
import com.example.sample.model.Product;
import com.example.sample.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public GETApiV1FindAllProductsResponseDTO findAll() {
        List<GETApiV1FindProductByIdResponseDTO> products = productRepository.findAll().stream()
                .map(this::mapToFindByIdResponseDTO)
                .collect(Collectors.toList());
        
        return GETApiV1FindAllProductsResponseDTO.builder()
                .products(products)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public GETApiV1FindProductByIdResponseDTO findById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        return mapToFindByIdResponseDTO(product);
    }

    @Override
    @Transactional
    public POSTApiV1CreateProductResponseDTO create(POSTApiV1CreateProductRequestDTO requestDTO) {
        Product product = Product.builder()
                .name(requestDTO.getName())
                .price(requestDTO.getPrice())
                .build();
        
        Product savedProduct = productRepository.save(product);
        
        return POSTApiV1CreateProductResponseDTO.builder()
                .id(savedProduct.getId())
                .message("Product created successfully")
                .build();
    }

    @Override
    @Transactional
    public GETApiV1FindProductByIdResponseDTO update(UUID id, PUTApiV1UpdateProductRequestDTO requestDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        
        Product updatedProduct = productRepository.save(product);
        
        return mapToFindByIdResponseDTO(updatedProduct);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        
        productRepository.deleteById(id);
    }
    
    private GETApiV1FindProductByIdResponseDTO mapToFindByIdResponseDTO(Product product) {
        return GETApiV1FindProductByIdResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}