package com.letscode.pablo.moviesbattle.infrastructure.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class CommaIntegerDeserializer extends JsonDeserializer<Integer> {
    @Override
    public Integer deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        String floatString = parser.getText();
        if (floatString.contains(",")) {
            floatString = floatString.replace(",", "");
        }
        return Integer.valueOf(floatString);
    }
}
