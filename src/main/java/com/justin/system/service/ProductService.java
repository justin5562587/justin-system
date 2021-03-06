package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.request.ReqCreateProductDTO;
import com.justin.system.entity.request.ReqUpdateProductDTO;
import com.justin.system.mapper.ProductMapper;
import com.justin.system.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public ResponseWrapper createProduct(ReqCreateProductDTO reqCreateProductDTO) {
        Product product = new Product();

        Long currentTime = System.currentTimeMillis();
        product.setCreateTime(currentTime);
        product.setUpdateTime(currentTime);

        product.setDescription(reqCreateProductDTO.getDescription());
        product.setImgUrl(reqCreateProductDTO.getImgUrl());
        product.setName(reqCreateProductDTO.getName());
        product.setPrice(reqCreateProductDTO.getPrice());
        product.setPointPrice(reqCreateProductDTO.getPointPrice());

        productMapper.save(product);

        return ResponseWrapper.success("Create Product Successfully");
    }

    public ResponseWrapper updateProduct(ReqUpdateProductDTO reqUpdateProductDTO) {
        Product product = productMapper.getProductById(reqUpdateProductDTO.getId());
        if (product != null) {
            productMapper.updateProduct(reqUpdateProductDTO);
            return ResponseWrapper.success("Update Product Successfully");
        }
        return ResponseWrapper.fail("Product Not Found");
    }

    public ResponseWrapper getProduct(Long id) {
        Product product = productMapper.getProductById(id);
        return product == null ?
                ResponseWrapper.fail("Product Not Existed") :
                ResponseWrapper.success(product);
    }

    public ResponseWrapper getProductList(int pageNumber, int pageSize) {
        List<Product> products = productMapper.getProductList(pageNumber * pageSize, pageSize);
        Map<String, Object> result = new HashMap<>();
        result.put("pageSize", pageSize);
        result.put("pageNumber", pageNumber);
        result.put("content", products);
        return ResponseWrapper.success(result);
    }

    public ResponseWrapper deleteProduct(Long id) {
        productMapper.deleteProduct(id);
        return ResponseWrapper.fail("Delete Product Successfully");
    }

}
