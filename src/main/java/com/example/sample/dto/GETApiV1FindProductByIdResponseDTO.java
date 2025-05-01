package com.example.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GETApiV1FindProductByIdResponseDTO {
    private UUID id;
    private String name;
    private BigDecimal price;
}