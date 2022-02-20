package com.letscode.pablo.moviesbattle.dataprovider.impl;

import com.letscode.pablo.moviesbattle.dataprovider.jpa.UserRepositoryJpa;
import com.letscode.pablo.moviesbattle.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
}