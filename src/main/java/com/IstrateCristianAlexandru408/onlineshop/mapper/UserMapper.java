package com.IstrateCristianAlexandru408.onlineshop.mapper;

import com.IstrateCristianAlexandru408.onlineshop.dto.User;
import com.IstrateCristianAlexandru408.onlineshop.entity.UserEntity;

public class UserMapper {
    private static UserMapper instance;

    private UserMapper() {
    }

    public static UserMapper getInstance() {
        if (instance == null) {
            instance = new UserMapper();
        }
        return instance;
    }

    public User toDto(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setRole(userEntity.getRole());
        return user;
    }

    public UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setRole(user.getRole());
        return userEntity;
    }


}
