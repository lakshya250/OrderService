package com.example.OrderService.service;

import com.example.OrderService.clients.ProductServiceClient;
import com.example.OrderService.dto.CreateOrderResponseDTO;
import com.example.OrderService.dto.OrderItemDTO;
import com.example.OrderService.dto.OrderRequestDTO;
import com.example.OrderService.dto.ProductDTO;
import com.example.OrderService.entity.Order;
import com.example.OrderService.entity.OrderItem;
import com.example.OrderService.mappers.OrderItemMapper;
import com.example.OrderService.mappers.OrderMapper;
import com.example.OrderService.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService{

    private final OrderRepository orderRepository;
    private final ProductServiceClient productClient;

    public OrderService(OrderRepository orderRepository, ProductServiceClient productClient){
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }
    @Override
    public CreateOrderResponseDTO createOrder(OrderRequestDTO request) {
        //Persist the order in order table

        Order order = OrderMapper.toEntity(request);
        List<OrderItem> items = new ArrayList<>();

        for(OrderItemDTO itemDTO : request.getItems()){

            //fetch the product details for every items

            ProductDTO product = productClient.getProductById(itemDTO.getProductId());
            double pricePerUnit = product.getPrice();
            double totalPrice = pricePerUnit * itemDTO.getQuantity();

            OrderItem item = OrderItemMapper.OrderItemRequestDTOtoOrderItemEntity(itemDTO,order,pricePerUnit,totalPrice);

            items.add(item);
        }

        order.setItems(items);
        Order createdOrder = orderRepository.save(order);
        return OrderMapper.toCreateOrderResponseDTO(createdOrder);
    }
}
