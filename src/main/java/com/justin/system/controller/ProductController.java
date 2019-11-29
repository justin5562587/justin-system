package com.justin.system.controller;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateProductDTO;
import com.justin.system.entity.request.ReqUpdateProductDTO;
import com.justin.system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseWrapper createProduct(@RequestBody ReqCreateProductDTO reqCreateProductDTO) {
        return productService.createProduct(reqCreateProductDTO);
    }

    @GetMapping("/detail")
    public ResponseWrapper getProduct(@RequestParam Long id) {
        return productService.getProduct(id);
    }

    @GetMapping("/list")
    public ResponseWrapper getProductList() {
        return productService.getProductList();
    }

    @PutMapping("/update")
    public ResponseWrapper updateProduct(@RequestBody ReqUpdateProductDTO reqUpdateProductDTO) {
        return productService.updateProduct(reqUpdateProductDTO);
    }

    @DeleteMapping("/delete")
    public ResponseWrapper deleteProduct(@RequestParam Long id) {
        return productService.deleteProduct(id);
    }

}
