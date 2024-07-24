package com.example.inventoryservice.repository;

import com.example.inventoryservice.model.Tb_Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Tb_OrderRepository extends JpaRepository<Tb_Order, Long> {
}