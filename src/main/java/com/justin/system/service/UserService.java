package com.justin.system.service;

import com.justin.system.entity.basic.PageEntity;
import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.entity.enums.ErrorTypeEnum;
import com.justin.system.entity.request.ReqCreateUserDTO;
import com.justin.system.entity.request.ReqUpdateUserDTO;
import com.justin.system.entity.search.SearchUserDTO;
import com.justin.system.mapper.UserMapper;
import com.justin.system.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public ResponseWrapper getUserList(int pageNumber, int pageSize) {
        List<User> userList = userMapper.getUserList(pageNumber * pageSize, pageSize);
        Map<String, Object> result = PageEntity.renderPageableRet(pageNumber, pageSize, userList);
        return ResponseWrapper.success(result);
    }

    public ResponseWrapper getUserDetail(SearchUserDTO searchUserDTO) {
        User retUser = userMapper.getUserByParams(searchUserDTO);
        return retUser != null ?
                ResponseWrapper.success(retUser) :
                ResponseWrapper.fail("User Not Found");
    }

    public ResponseWrapper createUser(ReqCreateUserDTO params) {
        try {
            User newUser = new User();

            // encode password
            String bCryptPassword = bCryptPasswordEncoder.encode(params.getPassword());
            newUser.setPassword(bCryptPassword);

            // handle time
            Long currentTime = System.currentTimeMillis();
            newUser.setCreateTime(currentTime);
            newUser.setUpdateTime(currentTime);

            newUser.setUserType(params.getUserType());
            newUser.setEmail(params.getEmail());
            newUser.setUsername(params.getUsername());
            newUser.setPoints(params.getPoints());

            userMapper.save(newUser);

            return ResponseWrapper.success("Create User Successfully");
        } catch (Exception e) {
            return ResponseWrapper.fail(e.getCause());
        }
    }

    public ResponseWrapper updateUser(ReqUpdateUserDTO params) {
        try {
            User user = new User();
            user.setId(params.getId());
            user.setPoints(params.getPoints());
            user.setUserType(params.getUserType());

            userMapper.updateUser(params);

            return ResponseWrapper.success("Update User Successfully");
        } catch (Exception e) {
            return ResponseWrapper.fail(e.getCause());
        }
    }

    public ResponseWrapper updateUserPassword(ReqUpdateUserDTO params) {
        try {
            SearchUserDTO searchUserDTO = new SearchUserDTO();
            searchUserDTO.setId(params.getId());
            User user = userMapper.getUserByParams(searchUserDTO);
            boolean isOldPasswordMatch = bCryptPasswordEncoder.matches(params.getOldPassword(), user.getPassword());
            if (isOldPasswordMatch) {
                String newPassword = bCryptPasswordEncoder.encode(params.getPassword());
                userMapper.updateUserPassword(newPassword, params.getId());
                return ResponseWrapper.success("Update User Password Successfully");
            } else {
                return ResponseWrapper.fail("Old Password is not matched");
            }
        } catch (Exception e) {
            return ResponseWrapper.fail(e.getCause());
        }
    }

    public ResponseWrapper deleteUser(Long id) {
        try {
            userMapper.deleteUserById(id);
            return ResponseWrapper.success("Delete User Successfully");
        } catch (Exception e) {
            return ResponseWrapper.fail(e.getCause());
        }
    }

}
