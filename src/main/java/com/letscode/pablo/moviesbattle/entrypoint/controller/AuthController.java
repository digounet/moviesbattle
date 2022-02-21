package com.letscode.pablo.moviesbattle.entrypoint.controller;

import com.letscode.pablo.moviesbattle.entrypoint.entity.UserLoginResponse;
import com.letscode.pablo.moviesbattle.entrypoint.entity.UserRegisterResponse;
import com.letscode.pablo.moviesbattle.entrypoint.entity.UserRequest;
import com.letscode.pablo.moviesbattle.infrastructure.constants.HttpResourcesPaths;
import com.letscode.pablo.moviesbattle.infrastructure.exception.UserAlreadyExistsException;
import com.letscode.pablo.moviesbattle.service.TokenService;
import com.letscode.pablo.moviesbattle.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Authentication")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Authenticate the user and returns a bearer token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User authenticated"),
            @ApiResponse(code = 403, message = "Login failed"),
            @ApiResponse(code = 500, message = "Generic exception")
    })
    @PostMapping(path = HttpResourcesPaths.AUTH_RESOURCE)
    public ResponseEntity<UserLoginResponse> authenticate(@RequestBody @Validated UserRequest authenticationRequest) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        var authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var token = tokenService.generateToken(authentication);

        return ResponseEntity.ok(UserLoginResponse.builder().type("Bearer").token(token).build());
    }

    @ApiOperation(value = "Register a new gamer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User registered"),
            @ApiResponse(code = 403, message = "Authorization failed"),
            @ApiResponse(code = 500, message = "Username already in use")
    })
    @PostMapping(path = HttpResourcesPaths.REGISTER_RESOURCE)
    public ResponseEntity<?> register(@RequestBody @Validated UserRequest registerRequest) {
        try {
            var savedUser = userService.register(registerRequest.getUsername(), registerRequest.getPassword());
            var response = UserRegisterResponse.builder()
                    .username(savedUser.getUsername())
                    .id(savedUser.getId())
                    .build();

            return ResponseEntity.ok(response);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity
                    .badRequest()
                    .body("Username already exists!");
        }
    }
}
