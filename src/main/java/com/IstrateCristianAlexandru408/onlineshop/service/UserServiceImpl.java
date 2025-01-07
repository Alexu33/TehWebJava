package com.IstrateCristianAlexandru408.onlineshop.service;

import com.IstrateCristianAlexandru408.onlineshop.dto.User;
import com.IstrateCristianAlexandru408.onlineshop.dto.UserCreation;
import com.IstrateCristianAlexandru408.onlineshop.entity.Role;
import com.IstrateCristianAlexandru408.onlineshop.entity.UserEntity;
import com.IstrateCristianAlexandru408.onlineshop.exception.UserAlreadyExistsException;
import com.IstrateCristianAlexandru408.onlineshop.exception.UserNotFoundException;
import com.IstrateCristianAlexandru408.onlineshop.mapper.UserMapper;
import com.IstrateCristianAlexandru408.onlineshop.repository.UserRepository;
import com.IstrateCristianAlexandru408.onlineshop.util.ErrorMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper.getInstance()::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format(ErrorMessageUtils.USER_NOT_FOUND, id)));

        return UserMapper.getInstance().toDto(userEntity);
    }

    @Override
    public User createUser(UserCreation userCreation) {

        if (userRepository.existsByUsername(userCreation.getUsername())) {
            throw new UserAlreadyExistsException(String.format(ErrorMessageUtils.USER_USERNAME_ALREADY_EXISTS, userCreation.getUsername()));
        }
        if (userRepository.existsByEmail(userCreation.getEmail())) {
            throw new UserAlreadyExistsException(String.format(ErrorMessageUtils.USER_EMAIL_ALREADY_EXISTS, userCreation.getEmail()));
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userCreation.getUsername());
        userEntity.setPassword(userCreation.getPassword());
        userEntity.setEmail(userCreation.getEmail());
        userEntity.setRole(Role.CUSTOMER);

        UserEntity savedUserEntity = userRepository.save(userEntity);
        return UserMapper.getInstance().toDto(savedUserEntity);
    }

    @Override
    public User updateUser(User user) {

        UserEntity userEntity = userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException(String.format(ErrorMessageUtils.USER_NOT_FOUND, user.getId())));

        if (!user.getUsername().equals(userEntity.getUsername())) {
            if (userRepository.existsByUsername(user.getUsername())) {
                throw new UserAlreadyExistsException(String.format(ErrorMessageUtils.USER_USERNAME_ALREADY_EXISTS, user.getUsername()));
            }
        }
        if (!user.getEmail().equals(userEntity.getEmail())) {
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new UserAlreadyExistsException(String.format(ErrorMessageUtils.USER_EMAIL_ALREADY_EXISTS, user.getEmail()));
            }
        }

        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setRole(user.getRole());
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return UserMapper.getInstance().toDto(savedUserEntity);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format(ErrorMessageUtils.USER_NOT_FOUND, id)));
        userRepository.deleteById(id);
    }

}