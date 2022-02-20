package com.letscode.pablo.moviesbattle.service;

import com.letscode.pablo.moviesbattle.dataprovider.UserRepository;
import com.letscode.pablo.moviesbattle.entity.User;
import com.letscode.pablo.moviesbattle.infrastructure.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optional = userRepository.findByUsername(username);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new UsernameNotFoundException("Login failed");
        }
    }

    public User register(String username, String password) throws UserAlreadyExistsException {
        var optional = userRepository.findByUsername(username);

        if (optional.isEmpty()) {
            var passwordEncoder = new BCryptPasswordEncoder();

            var user = new org.springframework.security.core.userdetails.User(username, passwordEncoder.encode(password), new ArrayList<>());
            return userRepository.save(new User(1, user.getUsername(), user.getPassword()));
        } else {
            throw new UserAlreadyExistsException("Please, select another username.");
        }
    }
}
