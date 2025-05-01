package com.example.sample.controller;

import com.example.sample.dto.GETApiV1FindAllProductsResponseDTO;
import com.example.sample.dto.GETApiV1FindProductByIdResponseDTO;
import com.example.sample.dto.POSTApiV1CreateProductRequestDTO;
import com.example.sample.dto.POSTApiV1CreateProductResponseDTO;
import com.example.sample.dto.PUTApiV1UpdateProductRequestDTO;
import com.example.sample.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Product API", description = "API for managing products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Returns a list of all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of products",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GETApiV1FindAllProductsResponseDTO.class)))
    })
    public ResponseEntity<GETApiV1FindAllProductsResponseDTO> findAll() {
        GETApiV1FindAllProductsResponseDTO responseDTO = productService.findAll();
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Returns a product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved product",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GETApiV1FindProductByIdResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<GETApiV1FindProductByIdResponseDTO> findById(
            @Parameter(description = "Product ID", required = true)
            @PathVariable("id") UUID id) {
        try {
            GETApiV1FindProductByIdResponseDTO responseDTO = productService.findById(id);
            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create a new product", description = "Creates a new product and returns the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = POSTApiV1CreateProductResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<POSTApiV1CreateProductResponseDTO> create(
            @Parameter(description = "Product to create", required = true, 
                    schema = @Schema(implementation = POSTApiV1CreateProductRequestDTO.class))
            @Valid @RequestBody POSTApiV1CreateProductRequestDTO requestDTO) {
        POSTApiV1CreateProductResponseDTO responseDTO = productService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing product", description = "Updates an existing product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GETApiV1FindProductByIdResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<GETApiV1FindProductByIdResponseDTO> update(
            @Parameter(description = "Product ID", required = true)
            @PathVariable("id") UUID id,
            @Parameter(description = "Updated product", required = true, 
                    schema = @Schema(implementation = PUTApiV1UpdateProductRequestDTO.class))
            @Valid @RequestBody PUTApiV1UpdateProductRequestDTO requestDTO) {
        try {
            GETApiV1FindProductByIdResponseDTO responseDTO = productService.update(id, requestDTO);
            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Deletes a product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "Product ID", required = true)
            @PathVariable("id") UUID id) {
        try {
            productService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}