package com.cgl.userservice.web.controllers.impl;

import com.cgl.userservice.data.entities.User;
import com.cgl.userservice.data.enums.Role;
import com.cgl.userservice.services.LoginService;
import com.cgl.userservice.services.UserService;
import com.cgl.userservice.utils.JwtTools;
import com.cgl.userservice.utils.mapper.MapperUser;
import com.cgl.userservice.web.dto.RequestDto;
import com.cgl.userservice.web.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AuthControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerImplTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private JwtTools jwtTools;

    @MockitoBean
    private LoginService loginService;

    @MockitoBean
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void login() throws Exception {
        RequestDto requestDto = new RequestDto("ftk@f.com", "1243456");
        User user = new User("123", "ftk", "ftk@f.com", "123456", Role.SELLER, null);
        when(userService.getByEmail(requestDto.getEmail())).thenReturn(user);
        when(passwordEncoder.matches(requestDto.getPassword(), user.getPassword())).thenReturn(true);
        when(jwtTools.generateToken(user)).thenReturn("token");
        mockMvc.perform(post("/api/v1/auth/login")
                .content(objectMapper.writeValueAsString(requestDto))
                .contentType("application/json"))
                .andExpect(jsonPath("$.token").value("token"));    }

    @Test
    void register() throws Exception{
        UserDto userDto =  new UserDto("123", "ftk", "ftk@f.com", "123456", "SELLER", null);
        User user = new User("123", "ftk", "ftk@f.com", "123456", Role.SELLER, null);
        when(userService.getByEmail(userDto.getEmail())).thenReturn(null);
        when(userService.create(any(User.class))).thenReturn(user);
        mockMvc.perform(post("/api/v1/auth/register")
                .content(objectMapper.writeValueAsString(userDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.response.name").value(user.getName()));
    }
}