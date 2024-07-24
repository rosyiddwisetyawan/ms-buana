package com.example.inventoryservice;

import com.example.inventoryservice.model.Product;
import com.example.inventoryservice.service.InventoryService;
import org.junit.jupiter.api.Test;

public class InventoryServiceTest {

    InventoryService service;
    @Test
    public void test(){
        Product pd = new Product();
        pd.setQuantity(30);
        pd.setName("pensil");
        pd.setPrice(1000);
        Product pdt = service.addProduct(pd).getProduct();
        if(pdt==pd){
            System.out.println("betul");
        }

    }
}
