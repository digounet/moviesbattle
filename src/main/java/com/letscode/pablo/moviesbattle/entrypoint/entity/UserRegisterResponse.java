package com.letscode.pablo.moviesbattle.entrypoint.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterResponse {
    private int id;
    private String username;
}
