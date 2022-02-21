package com.letscode.pablo.moviesbattle.entrypoint.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseResponseTest {

    @Test
    void testBaseResponse() {
        var response = new BaseResponse<>("Olá", "testes");
        assertEquals("Olá", response.getMessage());
        assertEquals("testes", response.getObject());
    }

    @Test
    void testEquals() {
        var response1 = new BaseResponse<>("Olá", "testes");
        var response2 = new BaseResponse<>("Olá", "testes");

        assertEquals(response1, response2);
    }

    @Test
    void testNotEquals() {
        var response1 = new BaseResponse<>("Olá", "testes");
        var response2 = new BaseResponse<>("Olá ", "testes");

        assertNotEquals(response1, response2);
    }
}