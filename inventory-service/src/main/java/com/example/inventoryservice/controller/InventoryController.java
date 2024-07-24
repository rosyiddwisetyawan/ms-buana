package com.example.inventoryservice.controller;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.model.Product;
import com.example.inventoryservice.model.ReqProduct;
import com.example.inventoryservice.model.Tb_Order;
import com.example.inventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;


    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/product/{id}")
    public Inventory getProductId(@PathVariable Long productId){
        return inventoryService.getInventoryByProductId(productId);
    }

    @PostMapping("/add")
    public Inventory addProduct(@RequestBody Product product){
        return inventoryService.addProduct(product);
    }

    @PostMapping("/update/quantity")
    public Inventory updateProduct(@RequestBody ReqProduct reqProduct){
        return inventoryService.updateQuantity(reqProduct);
    }

    @PostMapping("/order")
    public Tb_Order orderProduct(@RequestBody ReqProduct reqProduct){
        return inventoryService.order(reqProduct);
    }
}
