package com.justin.system.mapper;

import com.justin.system.models.ProductOrder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProductOrderMapper {

    @Insert("insert into product_order_table(id, create_time, update_time, status, buy_id, handlerId) " +
            "values(#{id}, #{createTime}, #{updateTime}, #{status}, #{buyId}, #{handlerId})")
    ProductOrder createProductOrder(ProductOrder productOrder);

    @Insert("<script>" +
            "insert into product_order_related_table (order_id, product_id) values "
            + "<foreach collection='productIds' item='productId' open='(' separator=',' close=')'>"
            + "(#{productOrderId}, #{productId}),"
            + "</foreach>"
            + "</script>")
    void createProductOrderRelated(Long productOrderId, Long[] productIds);

    @Update("")
    void updateProductOrder();

    @Select("select * from product_order_table where id=#{id}")
    ProductOrder getProductOrderById(Long id);

    @Select("select * from product_order_table limit #{offset}, #{pageSize}")
    List<ProductOrder> getProductOrderList(int offset, int pageSize);

    @Update("update product_order_table set status=#{status} handler_id=#{handlerId} where id=#{id}")
    void changeProductOrderStatus(String status, Long handlerId, Long id);

    @Update("update product_order_table set deleted=1 where id=#{id}")
    void deleteProductOrder(Long id);

    @Delete("drop from product_order_table where id=#{id}")
    void deleteProductOrderPhysical(Long id);
}
