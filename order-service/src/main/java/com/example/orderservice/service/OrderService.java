package com.example.orderservice.service;

import com.example.orderservice.model.Tb_Order;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    private final RestTemplate restTemplate;

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Tb_Order addOrder(Tb_Order tbOrder){
        String url = "http://localhost:8081/inventory/order"; // The URL of the Inventory Service
        Tb_Order tb = restTemplate.postForObject(url, tbOrder, Tb_Order.class);

        return tb;
    }
}
