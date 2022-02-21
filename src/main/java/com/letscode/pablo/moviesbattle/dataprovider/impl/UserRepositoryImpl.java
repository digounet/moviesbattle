package com.letscode.pablo.moviesbattle.dataprovider.impl;

import com.letscode.pablo.moviesbattle.dataprovider.UserRepository;
import com.letscode.pablo.moviesbattle.dataprovider.jpa.UserRepositoryJpa;
import com.letscode.pablo.moviesbattle.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserRepositoryJpa userRepositoryJpa;

    @Override
    public Optional<User> findById(Integer id) {
        return userRepositoryJpa.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepositoryJpa.getByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepositoryJpa.save(user);
    }

    @Override
    public List<User> loadRanking(int count) {
        return userRepositoryJpa.loadRanking(count);
    }
}
