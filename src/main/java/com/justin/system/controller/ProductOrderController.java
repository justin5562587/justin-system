package com.justin.system.controller;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateProductOrderDTO;
import com.justin.system.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "product-order")
public class ProductOrderController {

    @Autowired
    private ProductOrderService productOrderService;

    @GetMapping("/")
    public ResponseWrapper getProductOrder(@RequestParam Long id) {
        return productOrderService.getProductOrderById(id);
    }

    @GetMapping("/list")
    public ResponseWrapper getProductOrderList(
            @RequestParam int pageNumber,
            @RequestParam int pageSize
    ) {
        return productOrderService.getProductOrderList(pageNumber, pageSize);
    }

    @DeleteMapping("/")
    public ResponseWrapper deleteProductOrder(@RequestParam Long id) {
        return productOrderService.deleteProductOrder(id);
    }

    @PostMapping("/create")
    public ResponseWrapper createProductOrder(
            @RequestHeader("Authorization") String token,
            @RequestBody ReqCreateProductOrderDTO params) {
        return productOrderService.createProductOrder(params, token);
    }

    @PostMapping("/process")
    public ResponseWrapper processProductOrder() {
        return productOrderService.changeProductOrderStatus();
    }

    @PostMapping("/cancel")
    public ResponseWrapper cancelProducerOrder() {
        return productOrderService.changeProductOrderStatus();
    }

    @PostMapping("/finish")
    public ResponseWrapper finishProductOrder() {
        return productOrderService.changeProductOrderStatus();
    }
}
