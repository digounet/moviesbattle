package com.letscode.pablo.moviesbattle.infrastructure.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class CommaIntegerDeserializerTest {
    private ObjectMapper mapper;
    private CommaIntegerDeserializer deserializer;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        deserializer = new CommaIntegerDeserializer();
    }

    @Test
    public void deserialisesToInt() throws IOException {
        String json = String.format("{\"value\":%s}", "\"123,200\"");
        Number deserialisedNumber = deserialiseNumber(json);
        assertThat(deserialisedNumber, instanceOf(Integer.class));
        assertThat(deserialisedNumber, is(equalTo(123200)));
    }

    private Number deserialiseNumber(String json) throws IOException {
        InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        JsonParser parser = mapper.getFactory().createParser(stream);
        DeserializationContext ctxt = mapper.getDeserializationContext();
        parser.nextToken();
        parser.nextToken();
        parser.nextToken();
        return deserializer.deserialize(parser, ctxt);
    }
}