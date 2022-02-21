package com.letscode.pablo.moviesbattle.dataprovider.impl;

import com.letscode.pablo.moviesbattle.dataprovider.jpa.UserRepositoryJpa;
import com.letscode.pablo.moviesbattle.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserRepositoryImplTest {

    @Mock
    UserRepositoryJpa userRepositoryJpa;

    @InjectMocks
    UserRepositoryImpl userRepository;

    @Test
    void testSaveSuccess() {
        var user = new User();
        user.setUsername("teste");
        user.setPassword("123456");

        when(userRepositoryJpa.save(Mockito.any())).thenReturn(user);

        var savedUser = userRepository.save(user);

        assertNotNull(savedUser);
        assertEquals(user.getUsername(), savedUser.getUsername());
        assertEquals(user.getPassword(), savedUser.getPassword());
    }

    @Test
    void testFindById() {
        var user = new User();
        user.setUsername("teste");
        user.setPassword("123456");

        when(userRepositoryJpa.findById(Mockito.any())).thenReturn(Optional.of(user));

        var userResponse = userRepository.findById(user.getId());

        assertNotNull(userResponse);
        assertTrue(userResponse.isPresent());
        assertEquals(user.getUsername(), userResponse.get().getUsername());

    }

    @Test
    void testFindByUserName() {
        var user = new User();
        user.setUsername("teste");
        user.setPassword("123456");

        when(userRepositoryJpa.getByUsername(Mockito.any())).thenReturn(Optional.of(user));

        var userResponse = userRepository.findByUsername(user.getUsername());

        assertNotNull(userResponse);
        assertTrue(userResponse.isPresent());
        assertEquals(user.getUsername(), userResponse.get().getUsername());

    }
}