package com.justin.system.service;

import com.justin.system.entity.basic.PageEntity;
import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.basic.SystemConstant;
import com.justin.system.entity.enums.ProductOrderEnum;
import com.justin.system.entity.request.ReqCreateProductOrderDTO;
import com.justin.system.entity.utils.JwtUtil;
import com.justin.system.mapper.ProductMapper;
import com.justin.system.mapper.ProductOrderMapper;
import com.justin.system.models.ProductOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductOrderService {

    @Autowired
    private ProductOrderMapper productOrderMapper;

    @Autowired
    private ProductMapper productMapper;

    public ResponseWrapper createProductOrder(ReqCreateProductOrderDTO params, String token) {
        try {
            ProductOrder productOrder = new ProductOrder();

            Long userId = JwtUtil.getClaim(token, SystemConstant.USER_ID).asLong();
            productOrder.setBuyerId(userId);

            Long[] productIds = params.getProductIds();
            productOrder.setProductIds(productIds);

            productOrder.setStatus(ProductOrderEnum.INITIAL.toString());

            // handle time
            Long currentTimeMillis = System.currentTimeMillis();
            productOrder.setCreateTime(currentTimeMillis);
            productOrder.setUpdateTime(currentTimeMillis);

            System.out.println(productOrder.toString());

            // 插入product_order数据
            ProductOrder newProductOrder = productOrderMapper.createProductOrder(productOrder);
            // 插入product_order_related关联数据
            productOrderMapper.createProductOrderRelated(newProductOrder.getId(), params.getProductIds());

            return ResponseWrapper.success(newProductOrder);
        } catch (Exception e) {
            return ResponseWrapper.fail(e.getCause());
        }
    }

    public ResponseWrapper updateProductOrder() {
        productOrderMapper.updateProductOrder();
        return null;
    }

    public ResponseWrapper deleteProductOrder(Long id) {
        productOrderMapper.deleteProductOrderPhysical(id);
        return ResponseWrapper.success("Delete ProductOrder Successfully");
    }

    public ResponseWrapper getProductOrderById(Long id) {
        ProductOrder productOrder = productOrderMapper.getProductOrderById(id);
        return productOrder == null ?
                ResponseWrapper.fail("ProductOrder Can Not Found") :
                ResponseWrapper.success(productOrder);
    }

    public ResponseWrapper getProductOrderList(int pageNumber, int pageSize) {
        List<ProductOrder> content = productOrderMapper.getProductOrderList(pageNumber * pageSize, pageSize);
        Map<String, Object> result = PageEntity.renderPageableRet(pageNumber, pageSize, content);
        return ResponseWrapper.success(result);
    }

    public ResponseWrapper changeProductOrderStatus() {
        try {
            productOrderMapper.changeProductOrderStatus();
            return ResponseWrapper.success("Change ProductOrder Status Successfully");
        } catch (Exception e) {
            return ResponseWrapper.fail(e.getCause());
        }
    }
}
