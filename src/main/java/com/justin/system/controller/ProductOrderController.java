package com.justin.system.controller;

import com.justin.system.entity.basic.ResponseWrapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "product-order")
public class ProductOrderController {

    @GetMapping("/")
    public ResponseWrapper getProductOrder(@RequestParam Long id) {
        return null;
    }

    @GetMapping("/list")
    public ResponseWrapper getProductOrderList(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return null;
    }

    @DeleteMapping("/")
    public ResponseWrapper deleteProductOrder(@RequestParam Long id) {
        return null;
    }

    @PostMapping("/create")
    public ResponseWrapper createProductOrder() {
        return null;
    }

    @PostMapping("/process")
    public ResponseWrapper processProductOrder() {
        return null;
    }

    @PostMapping("/cancel")
    public ResponseWrapper cancelProducerOrder() {
        return null;
    }

    @PostMapping("/finish")
    public ResponseWrapper finishProductOrder() {
        return null;
    }
}
