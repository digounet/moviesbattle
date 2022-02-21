package com.letscode.pablo.moviesbattle.infrastructure.filter;

import com.letscode.pablo.moviesbattle.dataprovider.UserRepository;
import com.letscode.pablo.moviesbattle.entity.User;
import com.letscode.pablo.moviesbattle.service.TokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class JwtRequestFilterTest {

    @Mock
    private TokenService tokenService;

    @Mock
    private UserRepository repository;

    @InjectMocks
    private JwtRequestFilter jwtRequestFilter;

    @Test
    void testDoInternalFilterValidToken() throws ServletException, IOException {
        var user = new User(1, "pablo", "123456", 1, 2 , 3);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn("Bearer 1234345677887");
        when(tokenService.isTokenValid(any())).thenReturn(true);
        when(repository.findById(any())).thenReturn(Optional.of(user));

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        var auth = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        assertNotNull(auth);
        assertEquals(user, auth);
    }

    @Test
    void testDoInternalFilterValidTokenNoUserFound() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn("Bearer 1234345677887");
        when(tokenService.isTokenValid(any())).thenReturn(true);
        when(repository.findById(any())).thenReturn(Optional.empty());

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        var auth = (User)SecurityContextHolder.getContext().getAuthentication();
        assertNull(auth);
    }

    @Test
    void testDoInternalFilterEmptyToken() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn("");
        when(tokenService.isTokenValid(any())).thenReturn(false);

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        var auth = (User)SecurityContextHolder.getContext().getAuthentication();
        assertNull(auth);
    }

    @Test
    void testDoInternalFilterNoBearer() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn("dsgsdgfsdkfjsdfasd");
        when(tokenService.isTokenValid(any())).thenReturn(false);

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        var auth = (User)SecurityContextHolder.getContext().getAuthentication();
        assertNull(auth);
    }
}