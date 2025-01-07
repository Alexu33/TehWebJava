package com.IstrateCristianAlexandru408.onlineshop.controller;

import com.IstrateCristianAlexandru408.onlineshop.dto.User;
import com.IstrateCristianAlexandru408.onlineshop.dto.UserCreation;
import com.IstrateCristianAlexandru408.onlineshop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("Test User");

        UserCreation mockUserCreation = new UserCreation();
        mockUserCreation.setUsername("Test User");
        mockUserCreation.setPassword("password");
    }

    @Test
    public void testIfMockMvcIsAutowired() {
        assertNotNull(mockMvc);

    }

    @Test
    void testGetAllUsers() throws Exception {
        List<User> users = Arrays.asList(mockUser);
        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("Test User"));
    }

    @Test
    void testGetUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(mockUser);

        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Test User"));
    }

    @Test
    void testCreateUser() throws Exception {
        when(userService.createUser(any(UserCreation.class))).thenReturn(mockUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"Test User\",\"password\":\"password\",\"email\":\"test@test.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Test User"));
    }

    @Test
    void testUpdateUser() throws Exception {
        when(userService.updateUser(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            return new User(user.getId(), user.getUsername(), user.getEmail(), user.getRole());
        });

        mockMvc.perform(put("/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"username\":\"Updated User\",\"password\":\"password\",\"email\":\"test@test.com\",\"role\": \"CUSTOMER\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Updated User"));
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/users/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
