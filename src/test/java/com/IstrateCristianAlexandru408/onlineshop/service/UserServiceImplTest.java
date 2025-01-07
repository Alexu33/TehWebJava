package com.IstrateCristianAlexandru408.onlineshop.service;

import com.IstrateCristianAlexandru408.onlineshop.dto.User;
import com.IstrateCristianAlexandru408.onlineshop.dto.UserCreation;
import com.IstrateCristianAlexandru408.onlineshop.entity.Role;
import com.IstrateCristianAlexandru408.onlineshop.entity.UserEntity;
import com.IstrateCristianAlexandru408.onlineshop.exception.UserAlreadyExistsException;
import com.IstrateCristianAlexandru408.onlineshop.exception.UserNotFoundException;
import com.IstrateCristianAlexandru408.onlineshop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserEntity mockUser;


    @BeforeEach
    void setUp() {
        mockUser = new UserEntity();
        mockUser.setId(1L);
        mockUser.setUsername("testuser");
        mockUser.setEmail("testuser@example.com");
        mockUser.setPassword("password");
        mockUser.setRole(Role.CUSTOMER);
    }


    @Test
    void testGetAllUsers_Success() {
        UserEntity mockUser2 = new UserEntity();
        mockUser2.setId(2L);
        mockUser2.setUsername("testuser2");
        mockUser2.setEmail("testuser2@example.com");
        mockUser2.setPassword("password");
        mockUser2.setRole(Role.CUSTOMER);

        when(userRepository.findAll()).thenReturn(List.of(mockUser, mockUser2));

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals(mockUser.getUsername(), users.get(0).getUsername());
        assertEquals(mockUser2.getUsername(), users.get(1).getUsername());
    }

    @Test
    void testGetAllUsers_NoUsers() {
        when(userRepository.findAll()).thenReturn(List.of());

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertTrue(users.isEmpty());
    }

    @Test
    void testGetUserById_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        User user = userService.getUserById(1L);

        assertNotNull(user);
        assertEquals(mockUser.getUsername(), user.getUsername());
    }

    @Test
    void testGetUserById_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(1L);
        });

        assertEquals("User with id 1 Not Found", exception.getMessage());
    }

    @Test
    void testCreateUser_Success() {
        when(userRepository.existsByUsername(mockUser.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(mockUser.getEmail())).thenReturn(false);
        when(userRepository.save(any(UserEntity.class))).thenReturn(mockUser);

        UserCreation userCreation = new UserCreation("testuser", "testuser@example.com", "password");
        User createdUser = userService.createUser(userCreation);

        assertNotNull(createdUser);
        assertEquals(mockUser.getUsername(), createdUser.getUsername());
        assertEquals(mockUser.getEmail(), createdUser.getEmail());
    }

    @Test
    void testCreateUser_UsernameAlreadyExists() {
        when(userRepository.existsByUsername(mockUser.getUsername())).thenReturn(true);

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> {
            userService.createUser(new UserCreation("testuser", "testuser@example.com", "password"));
        });

        assertEquals("User with username testuser Already Exists", exception.getMessage());
    }

    @Test
    void testCreateUser_EmailAlreadyExists() {
        when(userRepository.existsByEmail(mockUser.getEmail())).thenReturn(true);

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> {
            userService.createUser(new UserCreation("newuser","testuser@example.com", "password"));
        });

        assertEquals("User with email testuser@example.com Already Exists", exception.getMessage());
    }

    @Test
    void testDeleteUser_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteUser_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.deleteUser(1L);
        });

        assertEquals("User with id 1 Not Found", exception.getMessage());
    }

    @Test
    void testUpdateUser_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(userRepository.save(any(UserEntity.class))).thenReturn(mockUser);

        User updatedUser = userService.updateUser(new User(1L, "testuser", "testuser@example.com", Role.CUSTOMER));

        assertNotNull(updatedUser);
        assertEquals(mockUser.getUsername(), updatedUser.getUsername());
    }

    @Test
    void testUpdateUser_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser(new User(1L, "testuser", "testuser@example.com", Role.CUSTOMER));
        });

        assertEquals("User with id 1 Not Found", exception.getMessage());
    }
}