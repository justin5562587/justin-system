package com.justin.system.mapper;

import com.justin.system.models.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProductMapper {

    @Insert("insert into product_table(id, create_time, update_time, name, description, img_url, price, point_price, deleted) " +
            "values(#{id}, #{createTime}, #{updateTime}, #{name}, #{description}, #{imgUrl}, #{price}, #{pointPrice}, 0) ")
    void save(Product product);

    @Select("select * from product_table")
    List<Product> getProductList();

    @Select("select * from product_table where id=#{id}")
    Product getProductById(Long id);

    @Update("update product_table set deleted=1 where id=#{id}")
    void deleteProduct(Long id);

    @Update("update product_table")
    void updateProduct();
}
