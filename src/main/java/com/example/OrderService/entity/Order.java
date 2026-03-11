package com.example.OrderService.entity;

import com.example.OrderService.dto.OrderItemDTO;
import com.example.OrderService.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="orders")
public class Order extends BaseEntity{
    private Long userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;
}
