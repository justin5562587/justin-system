package com.justin.system.mapper;

import com.justin.system.models.ProductOrder;
import com.justin.system.models.ProductOrderRelated;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductOrderMapper {

    @Insert("insert into product_order_table(create_time, update_time, status, buyer_id) " +
            "values(#{createTime}, #{updateTime}, #{status}, #{buyerId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void createProductOrder(ProductOrder productOrder);

    @Insert("<script>" +
            "insert into product_order_related_table (order_id, product_id) values "
            + "<foreach collection='productIds' item='productId' separator=','>"
            + "(#{productOrderId}, #{productId})"
            + "</foreach>"
            + "</script>")
    void createProductOrderRelated(@Param("productOrderId") Long productOrderId, @Param("productIds") Long[] productIds);

    @Select("select * from product_order_table where id=#{id}")
    @Results({
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "buyerId", column = "buyer_id"),
            @Result(property = "handleId", column = "handler_id"),
    })
    ProductOrder getProductOrderById(Long id);

    @Select("Select * from product_order_related_table where order_id=#{productOrderId}")
    @Results({
            @Result(property = "productOrderId", column = "order_id"),
            @Result(property = "productId", column = "product_id"),
    })
    List<ProductOrderRelated> getProductOrderRelatedLists(Long productOrderId);

    @Select("select * from product_order_table limit #{offset}, #{pageSize}")
    List<ProductOrder> getProductOrderList(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Update("update product_order_table set status=#{status} handler_id=#{handlerId} update_time=#{updateTime} where id=#{id}")
    void changeProductOrderStatus(String status, Long handlerId, Long updateTime, Long id);

    @Update("update product_order_table set deleted=1 where id=#{id}")
    void deleteProductOrder(Long id);

    @Update("update product_order_table_related set deleted=1 where order_id=#{orderId}")
    void deleteProductOrderRelated(Long orderId);

}
