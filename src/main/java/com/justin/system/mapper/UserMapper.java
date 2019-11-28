package com.justin.system.mapper;

import com.justin.system.models.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {

    @Insert("insert into user_table(id, create_time, update_time, email, password, points, user_type, username) " +
            "values(#{id}, #{createTime}, #{updateTime}, #{email}, #{password}, #{points}, #{userType}, #{username});")
    void save(User user);

//    @Select("<script> " +
//            "select * from user_table where 1=1 "
//            +"limit #{page},#{size}"
//            + "</script>")
//    List<User> getUserList(int page, int size);

    @Select("select * from user_table;")
    List<User> getUserList();

    @Select("select * from user_table ut where ut.id=#{id};")
    User getUserById(Long id);

    @Select("<script> " +
            "select * from user_table where 1=1 "
            + "<if test='page != null'>"
            + "and email=${email} "
            + "</if>"
            + "</script>")
    User getUserByParams(Object SearchUserDTO);

    @Select("<script> " +
            "select * from user_table where 1=1 "
            + "<if test='page != null'>"
            + "and page=${page} "
            + "</if>"
            + "<if test='size != null'>"
            + "and id=${size} "
            + "</if>"
            + "</script>")
    User findUser(Object SearchUserDTO);

    @Delete("delete from user_table ut where ut.id=${id}")
    void deleteUserById(Long id);


}
