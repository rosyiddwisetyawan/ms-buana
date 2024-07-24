package com.example.inventoryservice.service;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.model.Product;
import com.example.inventoryservice.model.ReqProduct;
import com.example.inventoryservice.model.Tb_Order;
import com.example.inventoryservice.repository.InventoryRepository;
import com.example.inventoryservice.repository.ProductRepository;
import com.example.inventoryservice.repository.Tb_OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final Tb_OrderRepository orderRepository;

    public InventoryService(InventoryRepository inventoryRepository,
                            ProductRepository productRepository, Tb_OrderRepository orderRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public Inventory addProduct(Product product){
        Inventory inv = getInventoryByProductId(product.getId());
        if(inv==null){
            productRepository.save(product);

            Inventory inventory = new Inventory();
            inventory.setProduct(product);
            inventory.setStatus(true);
            return inventoryRepository.save(inventory);
        }else{
            return addQty(inv,product);
        }
    }

    public Inventory getInventory(Long inventoryId){
        return inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory item not found for id: " + inventoryId));
    }

    public Inventory getInventoryByProductId(Long productId){
        return inventoryRepository.findByProduct_Id(productId);
    }

    public Inventory updateQuantity(ReqProduct reqProduct){

        Inventory inv = getInventoryByProductId(reqProduct.getProductId());
        if(inv!=null){
            Product pr = inv.getProduct();
            pr.setQuantity(reqProduct.getQuantity());
            productRepository.save(pr);
        }
        return inv;
    }

    public Inventory addQty(Inventory inv, Product product){

        if(inv.getProduct().getQuantity()>0){
            int newQuantity = inv.getProduct().getQuantity() + product.getQuantity();
            product.setQuantity(newQuantity);
            productRepository.save(product);
        }

        return inv;
    }

    public Inventory reductQty(Inventory inv, int qty){

        if(inv.getProduct().getQuantity()>0){
            int newQuantity = inv.getProduct().getQuantity() - qty;
            Product product = inv.getProduct();
            product.setQuantity(newQuantity);
            productRepository.save(product);
        }

        return inv;
    }

    public Tb_Order order(ReqProduct reqProduct){
        Inventory inv = getInventoryByProductId(reqProduct.getProductId());
        Tb_Order tbOrder = new Tb_Order();
        if(inv!=null && inv.getProduct().getQuantity()>0){
            Product product = productRepository.findById(reqProduct.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            reductQty(inv,reqProduct.getQuantity());
            tbOrder = addOrder(reqProduct);
        }
        return tbOrder;
    }

    public Tb_Order addOrder(ReqProduct product){
        Tb_Order order = new Tb_Order();
        order.setProductId(product.getProductId());
        order.setQuantity(product.getQuantity());
        return orderRepository.save(order);
    }
}
