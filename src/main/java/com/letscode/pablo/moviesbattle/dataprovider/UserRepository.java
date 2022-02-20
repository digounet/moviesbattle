package com.letscode.pablo.moviesbattle.dataprovider;

import com.letscode.pablo.moviesbattle.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Integer id);

    Optional<User> findByUsername(String username);

    User save(User user);
}
