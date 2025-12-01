package io.bootify.file_upload.util;

import java.util.Locale;
import org.springframework.format.Formatter;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;


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
        return objectMapper.readValue(text, this);
    }

    @Override
    public String print(final T object, final Locale locale) {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

}
