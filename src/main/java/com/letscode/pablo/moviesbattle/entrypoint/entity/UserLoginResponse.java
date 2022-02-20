package com.letscode.pablo.moviesbattle.entrypoint.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponse {

    private String type;
    private String token;
}
