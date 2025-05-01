package com.example.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class POSTApiV1CreateProductResponseDTO {
    private UUID id;
    private String message;
}