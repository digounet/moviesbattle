package com.letscode.pablo.moviesbattle.entrypoint.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse<T> {
    private String message;

    private T object;
}
