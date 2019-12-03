package com.justin.system.mapper;

import com.justin.system.models.ProductOrder;
import com.justin.system.models.ProductOrderRelated;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface ProductOrderMapper {

    @Insert("insert into product_order_table(create_time, update_time, status, buyer_id) " +
            "values(#{createTime}, #{updateTime}, #{status}, #{buyerId})")
    void createProductOrder(ProductOrder productOrder);

    @Insert("<script>" +
            "insert into product_order_related_table (order_id, product_id) values "
            + "<foreach collection='productIds' item='productId' open='(' separator=',' close=')'>"
            + "(#{productOrderId}, #{productId}),"
            + "</foreach>"
            + "</script>")
    void createProductOrderRelated(Long productOrderId, Long[] productIds);

    @Select("select * from product_order_table where id=#{id}")
    @Results({
        @Result(column="create_time", property="createTime", jdbcType= JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType= JdbcType.BIGINT),
    })
    ProductOrder getProductOrderById(Long id);

    @Select("Select * from product_order_table where order_id=#{productOrderId}")
    List<ProductOrderRelated> getProductOrderRelatedLists(Long productOrderId);

    @Select("select * from product_order_table limit #{offset}, #{pageSize}")
    List<ProductOrder> getProductOrderList(int offset, int pageSize);

    @Update("update product_order_table set status=#{status} handler_id=#{handlerId} update_time=#{updateTime} where id=#{id}")
    void changeProductOrderStatus(String status, Long handlerId, Long updateTime, Long id);

    @Update("update product_order_table set deleted=1 where id=#{id}")
    void deleteProductOrder(Long id);

    @Delete("drop from product_order_table where id=#{id}")
    void deleteProductOrderPhysical(Long id);
}
