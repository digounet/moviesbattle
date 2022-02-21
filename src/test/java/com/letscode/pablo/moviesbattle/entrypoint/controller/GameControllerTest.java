package com.letscode.pablo.moviesbattle.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letscode.pablo.moviesbattle.dataprovider.UserRepository;
import com.letscode.pablo.moviesbattle.entity.Game;
import com.letscode.pablo.moviesbattle.infrastructure.constants.HttpResourcesPaths;
import com.letscode.pablo.moviesbattle.infrastructure.exception.GameAlreadyStartedException;
import com.letscode.pablo.moviesbattle.service.GameService;
import com.letscode.pablo.moviesbattle.service.TokenService;
import com.letscode.pablo.moviesbattle.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebMvcTest(GameController.class)
class GameControllerTest {

    @MockBean
    private GameService gameService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private UserService userService;

    @InjectMocks
    private GameController gameController;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(username="pablo",roles={"ADMIN"})
    void startGameSuccess() throws Exception {
        when(gameService.startGame(eq(1000))).thenReturn(new Game());

        var response =
                mvc.perform(MockMvcRequestBuilders.get(HttpResourcesPaths.START_GAME_RESOURCE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertNotNull(response.getResponse().getContentAsString());
    }
}