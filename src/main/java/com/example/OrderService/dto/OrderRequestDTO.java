package com.example.OrderService.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class OrderRequestDTO {
    private Long userId;
    private List<OrderItemDTO> items;
}
