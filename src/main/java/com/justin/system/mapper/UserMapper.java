package com.justin.system.mapper;

import com.justin.system.entity.request.ReqUpdateUserDTO;
import com.justin.system.entity.search.SearchUserDTO;
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

    @Select("<script> " +
            "select * from user_table where 1=1 "
            + "<if test='id != null'>"
            + "and id =#{id} "
            + "</if>"
            + "<if test='username != null'>"
            + "and username =#{username} "
            + "</if>"
            + "<if test='email != null'>"
            + "and email =#{email} "
            + "</if>"
            + "</script>")
    User getUserByParams(SearchUserDTO searchUserDTO);

    @Select("<script> " +
            "select * from user_table where 1=1 "
            + "limit #{page},#{size}"
            + "</script>")
    List<User> getUserList(int page, int size);

    List<User> getUserList();

    @Delete("delete from user_table ut where ut.id=${id}")
    void deleteUserById(Long id);

    @Update("update user_table set points=#{points}, user_type=#{userType} where id=#{id}")
    void updateUser(ReqUpdateUserDTO reqUpdateUserDTO);

    @Update("update uer_table set password=#{password} where id=#{id}")
    void updateUserPassword(String password, Long id);

}
