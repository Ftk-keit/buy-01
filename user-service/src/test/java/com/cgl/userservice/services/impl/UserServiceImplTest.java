package com.cgl.userservice.services.impl;

import com.cgl.userservice.data.entities.User;
import com.cgl.userservice.data.enums.Role;
import com.cgl.userservice.data.repositories.UserRepository;
import com.cgl.userservice.web.dto.ChangePasswordRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void updatePassword() {
        User user = new User("123", "ftk", "ftk@f.com", "123", Role.SELLER, null);
        when(userRepository.findById("123")).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(passwordEncoder.matches("123", "123")).thenReturn(true);
        ChangePasswordRequest request = ChangePasswordRequest.builder()
                .currentPassword("123")
                .newPassword("123")
                .build();
        Map<String, Object> response = new HashMap<>();
        response = userServiceImpl.updatePassword("123", request);
        assertEquals("Le mot de passe a été changé", response.get("message"));
    }
}