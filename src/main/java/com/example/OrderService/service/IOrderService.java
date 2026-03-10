package com.example.OrderService.service;

import com.example.OrderService.dto.CreateOrderResponseDTO;
import com.example.OrderService.dto.OrderRequestDTO;

public interface IOrderService {

    CreateOrderResponseDTO createOrder(OrderRequestDTO dto);
}
