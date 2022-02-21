package com.letscode.pablo.moviesbattle.entrypoint.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizzResponse {
    private String movieAId;
    private String movieA;
    private String movieBId;
    private String MovieB;
}
