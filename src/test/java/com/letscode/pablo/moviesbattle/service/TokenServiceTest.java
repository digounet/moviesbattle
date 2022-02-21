package com.letscode.pablo.moviesbattle.service;

import com.letscode.pablo.moviesbattle.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TokenServiceTest {

    @Autowired
    private TokenService tokenService;

    @Test
    void testGenerateToken() {
        var user = new User(1, "pablo", "12334546", 1, 2 ,3);
        var authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());

        var token = tokenService.generateToken(authentication);

        assertNotNull(token);
    }

    @Test
    void testIsTokenValid() {
        var token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJUlMiLCJzdWIiOiIxIiwiaWF0IjoxNjQ1Mzg3NTE0LCJleHAiOjE2NDU0NzM5MTR9.VW_jPYz0EEal2lYZWnFPo8n90or-CWCiEOjDAitNJPY";
        var isValid = tokenService.isTokenValid(token);
        assertTrue(isValid);
    }

    @Test
    void testIsTokenInvalid() {
        var token = "gyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJUlMiLCJzdWIiOiIxIiwiaWF0IjoxNjQ1Mzg3NTE0LCJleHAiOjE2NDU0NzM5MTR9.VW_jPYz0EEal2lYZWnFPo8n90or-CWCiEOjDAitNJPY";
        var isValid = tokenService.isTokenValid(token);
        assertFalse(isValid);
    }

    @Test
    void testGetTokenId() {
        var token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJUlMiLCJzdWIiOiIxIiwiaWF0IjoxNjQ1Mzg3NTE0LCJleHAiOjE2NDU0NzM5MTR9.VW_jPYz0EEal2lYZWnFPo8n90or-CWCiEOjDAitNJPY";
        var id = tokenService.getTokenId(token);
        assertEquals(1, id);
    }
}