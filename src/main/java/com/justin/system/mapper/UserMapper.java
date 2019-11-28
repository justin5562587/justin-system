package com.justin.system.mapper;

import com.justin.system.models.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    @Insert("insert into user_table(id, create_time, update_time, email, password, points, user_type, username) values(#{id}, #{createTime}, #{updateTime}, #{email}, #{password}, #{points}, #{userType}, #{username});")
    void save(User user);

//    @Delete("")
//    void deleteUserById(Long id);

    @Select("select * from user_table ut where ut.id = #{id};")
    User findUserById(Long id);

//    @Update("")
//    void updateUser(User user);


}
