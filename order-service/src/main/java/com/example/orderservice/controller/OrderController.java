package com.example.orderservice.controller;

import com.example.orderservice.model.Tb_Order;
import com.example.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/order")
    public Tb_Order orderProduct(@RequestBody Tb_Order tbOrder){
        return orderService.addOrder(tbOrder);
    }
}
