package com.example.OrderService.dto;

import com.example.OrderService.enums.OrderStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderResponseDTO {

    private Long orderId;
    private OrderStatus status;
}
