package com.letscode.pablo.moviesbattle.service;

import com.letscode.pablo.moviesbattle.dataprovider.UserRepository;
import com.letscode.pablo.moviesbattle.entity.User;
import com.letscode.pablo.moviesbattle.infrastructure.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Captor
    ArgumentCaptor<User> userArgumentCaptor;

    @Test
    void loadUserByUsernameFound() {

        var user = new User(1, "pablo", "1234556", 1, 2 , 3);

        when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));

        var userDetails = userService.loadUserByUsername("pablo");

        assertNotNull(userDetails);
        assertEquals("pablo", userDetails.getUsername());
    }

    @Test
    void loadUserByUsernameNotFound() {

        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("pablo"));

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Login failed"));
    }

    @Test
    void registerSuccess() throws UserAlreadyExistsException {
        var userToSave = new User(1, "pablo", "sfasdfasda", 1, 2 , 3);
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(userToSave);

        var newUSer = userService.register(userToSave.getUsername(), userToSave.getPassword());

        assertNotNull(newUSer);
        assertEquals(userToSave, newUSer);
    }

    @Test
    void registerAlreadyExists()  {
        var userToSave = new User(1, "pablo", "sfasdfasda", 1, 2 , 3);
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(userToSave));
        when(userRepository.save(any())).thenReturn(userToSave);

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> userService.register(userToSave.getUsername(), userToSave.getPassword()));

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Please, select another username."));
    }

    @Test
    void ranking() {

        var user = new User(1, "pablo", "1234556", 1, 2 , 3);

        when(userRepository.loadRanking(eq(10))).thenReturn(List.of(user));

        var ranking = userService.ranking();

        assertNotNull(ranking);
        assertEquals(1, ranking.size());
    }

    @Test
    void loadUserById() {

        var user = new User(1, "pablo", "1234556", 1, 2 , 3);

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        var userDetails = userService.loadUserById(10000);

        assertNotNull(userDetails);
        assertEquals("pablo", userDetails.getUsername());
    }

    @Test
    void loadUserByIdNotFound() {

        when(userRepository.findById(any())).thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> userService.loadUserById(10000));

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Login failed"));
    }

    @Test
    void updateSuccess() {

        var user = new User(1, "pablo", "1234556", 1, 2 , 3);

        when(userRepository.save(any())).thenReturn(user);
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));

        userService.update(user);

        verify(userRepository, times(1)).save(userArgumentCaptor.capture());
    }

    @Test
    void updateNoSuccess() {

        var user = new User(1, "pablo", "1234556", 1, 2 , 3);

        when(userRepository.save(any())).thenReturn(user);
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());

        userService.update(user);

        verify(userRepository, times(0)).save(userArgumentCaptor.capture());
    }
}