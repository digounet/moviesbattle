package com.letscode.pablo.moviesbattle.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final int userId = 1;
    private final String username = "pablo";
    private final String password = "123456";
    private final int games = 10;
    private final int hits = 9;
    private final int mistakes = 8;
    private final List<String> authorities = new ArrayList<>();

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(userId, username, password, games, hits, mistakes);
    }

    @Test
    void getAuthorities() {
        assertEquals(authorities.size(), (long) user.getAuthorities().size());
    }

    @Test
    void getPassword() {
        assertEquals(password, user.getPassword());
    }

    @Test
    void getUsername() {
        assertEquals(username, user.getUsername());
    }

    @Test
    void getTotalHits() {
        assertEquals(hits, user.getTotalHits());
    }

    @Test
    void getTotalGames() {
        assertEquals(games, user.getTotalGames());
    }

    @Test
    void getTotalMistakes() {
        assertEquals(mistakes, user.getTotalMistakes());
    }

    @Test
    void isAccountNonExpired() {
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        assertTrue(user.isEnabled());
    }

    @Test
    void getId() {
        assertEquals(userId, user.getId());
    }

    @Test
    void setId() {
        var newId = 10000;
        user.setId(newId);
        assertEquals(newId, user.getId());
    }

    @Test
    void setUsername() {
        var newUsername = "asdfghh";
        user.setUsername(newUsername);
        assertEquals(newUsername, user.getUsername());
    }

    @Test
    void setPassword() {
        var newPassword = "fgsdfgdfgsdgfsdfgsd";
        user.setPassword(newPassword);
        assertEquals(newPassword, user.getPassword());
    }

    @Test
    void setTotalMistakes() {
        var change = 1;
        user.setTotalMistakes(change);
        assertEquals(change, user.getTotalMistakes());
    }

    @Test
    void setTotalHits() {
        var change = 1;
        user.setTotalHits(change);
        assertEquals(change, user.getTotalHits());
    }

    @Test
    void setTotalGames() {
        var change = 1;
        user.setTotalGames(change);
        assertEquals(change, user.getTotalGames());
    }
}