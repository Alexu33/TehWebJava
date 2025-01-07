package com.IstrateCristianAlexandru408.onlineshop.service;

import com.IstrateCristianAlexandru408.onlineshop.dto.User;
import com.IstrateCristianAlexandru408.onlineshop.dto.UserCreation;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public User getUserById(Long id);

    public User createUser(UserCreation user);

    public User updateUser(User user);

    public void deleteUser(Long id);
}