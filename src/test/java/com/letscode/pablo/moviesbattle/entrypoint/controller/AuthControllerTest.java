package com.letscode.pablo.moviesbattle.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letscode.pablo.moviesbattle.dataprovider.UserRepository;
import com.letscode.pablo.moviesbattle.entity.User;
import com.letscode.pablo.moviesbattle.entrypoint.entity.UserLoginResponse;
import com.letscode.pablo.moviesbattle.entrypoint.entity.UserRegisterResponse;
import com.letscode.pablo.moviesbattle.entrypoint.entity.UserRequest;
import com.letscode.pablo.moviesbattle.infrastructure.constants.HttpResourcesPaths;
import com.letscode.pablo.moviesbattle.infrastructure.exception.UserAlreadyExistsException;
import com.letscode.pablo.moviesbattle.service.TokenService;
import com.letscode.pablo.moviesbattle.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testLoginSuccess() throws Exception {
        var loginRequest = UserRequest.builder()
                .username("pablo")
                .password("123456")
                .build();
        when(authenticationManager.authenticate(any())).thenReturn(new UsernamePasswordAuthenticationToken("pablo", "12345"));
        when(tokenService.generateToken(any())).thenReturn("any token");

        var response = mvc.perform(MockMvcRequestBuilders.post(HttpResourcesPaths.AUTH_RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertNotNull(response.getResponse().getContentAsString());
        var token = objectMapper.readValue(response.getResponse().getContentAsString(), UserLoginResponse.class);
        assertNotNull(token);
        assertEquals("any token", token.getToken());
        assertEquals("Bearer", token.getType());
    }

    @Test
    void testRegisterSuccess() throws Exception {
        var registerRequest = UserRequest.builder()
                .username("pablo")
                .password("123456")
                .build();

        when(userService.register(any(), any())).thenReturn(new User(1, "pablo", "12345"));

        var response = mvc.perform(MockMvcRequestBuilders.post(HttpResourcesPaths.REGISTER_RESOURCE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertNotNull(response.getResponse().getContentAsString());

        var user = objectMapper.readValue(response.getResponse().getContentAsString(), UserRegisterResponse.class);
        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("pablo", user.getUsername());
    }

    @Test
    void testRegisterDuplicated() throws Exception {
        var registerRequest = UserRequest.builder()
                .username("pablo")
                .password("123456")
                .build();

        when(userService.register(any(), any())).thenThrow(UserAlreadyExistsException.class);

        var response = mvc.perform(MockMvcRequestBuilders.post(HttpResourcesPaths.REGISTER_RESOURCE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        assertNotNull(response.getResponse().getContentAsString());
        assertEquals("Username already exists!", response.getResponse().getContentAsString());
    }
}