package com.justin.system.service;

import com.justin.system.entity.basic.PageEntity;
import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.basic.SystemConstant;
import com.justin.system.entity.enums.ProductOrderEnum;
import com.justin.system.entity.request.ReqCreateProductOrderDTO;
import com.justin.system.entity.utils.JwtUtil;
import com.justin.system.mapper.ProductMapper;
import com.justin.system.mapper.ProductOrderMapper;
import com.justin.system.models.Product;
import com.justin.system.models.ProductOrder;
import com.justin.system.models.ProductOrderRelated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductOrderService {

    @Autowired
    private ProductOrderMapper productOrderMapper;

    @Autowired
    private ProductMapper productMapper;

    private Long getUserIdFromToken(String token) {
        return JwtUtil.getClaim(token, SystemConstant.USER_ID).asLong();
    }

    public ResponseWrapper createProductOrder(ReqCreateProductOrderDTO params, String token) {
        ProductOrder productOrder = new ProductOrder();

        Long userId = getUserIdFromToken(token);
        productOrder.setBuyerId(userId);

        Long[] productIds = params.getProductIds();
        productOrder.setProductIds(productIds);

        productOrder.setStatus(ProductOrderEnum.INITIAL.toString());

        // handle time
        Long currentTimeMillis = System.currentTimeMillis();
        productOrder.setCreateTime(currentTimeMillis);
        productOrder.setUpdateTime(currentTimeMillis);

        // 插入product_order数据
        productOrderMapper.createProductOrder(productOrder);
        System.out.println(id);
        // 插入product_order_related关联数据
//        productOrderMapper.createProductOrderRelated(newProductOrder.getId(), params.getProductIds());

        return ResponseWrapper.success("111");
    }

    // todo 后续需要修改为非物理删除，并且删除关联表的数据
    public ResponseWrapper deleteProductOrder(Long id) {
        productOrderMapper.deleteProductOrderPhysical(id);
        return ResponseWrapper.success("Delete ProductOrder Successfully");
    }

    public ResponseWrapper getProductOrderById(Long id) {
        ProductOrder productOrder = productOrderMapper.getProductOrderById(id);
        if (productOrder == null) {
            return ResponseWrapper.fail("ProductOrder Can Not Found");
        }

        Long productOrderId = productOrder.getId();
        List<ProductOrderRelated> productOrderRelateds = productOrderMapper.getProductOrderRelatedLists(productOrderId);
        List<Product> products = new ArrayList<>();
        for (ProductOrderRelated productOrderRelated : productOrderRelateds) {
            Product product = productMapper.getProductById(productOrderRelated.getProductId());
            products.add(product);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("products", products);
        result.put("orderDetail", productOrder);

        return ResponseWrapper.success(result);
    }

    public ResponseWrapper getProductOrderList(int pageNumber, int pageSize) {
        List<ProductOrder> content = productOrderMapper.getProductOrderList(pageNumber * pageSize, pageSize);
        Map<String, Object> result = PageEntity.renderPageableRet(pageNumber, pageSize, content);
        return ResponseWrapper.success(result);
    }

    // todo
    public ResponseWrapper changeProductOrderStatus(String token) {
        try {
            String status = ProductOrderEnum.PROCESSING.toString();
            Long handlerId = getUserIdFromToken(token);
            Long updateTime = System.currentTimeMillis();
            long updatedProductOrderId = 100;
            productOrderMapper.changeProductOrderStatus(status, handlerId, updateTime, updatedProductOrderId);
            return ResponseWrapper.success("Change ProductOrder Status Successfully");
        } catch (Exception e) {
            return ResponseWrapper.fail(e.getCause());
        }
    }
}
