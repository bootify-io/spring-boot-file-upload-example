package io.bootify.file_upload.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Locale;
import org.springframework.format.Formatter;


/**
 * Generic class for printing an Object as a JSON String and parsing a String to the given type.
 * Extends TypeReference to keep generic type information.
 */
public class JsonStringFormatter<T> extends TypeReference<T> implements Formatter<T> {

    private final ObjectMapper objectMapper;

    public JsonStringFormatter(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public T parse(final String text, final Locale locale) {
        try {
            return objectMapper.readValue(text, this);
        } catch (final JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String print(final T object, final Locale locale) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (final JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

}
