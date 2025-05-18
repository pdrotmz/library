package io.github.pdrotmz.libraryAPI.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = true)
public class LocalDataTimeConverter implements AttributeConverter<LocalDateTime, String> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public String convertToDatabaseColumn(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.format(formatter) : null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(String dbData) {
        return dbData != null ? LocalDateTime.parse(dbData, formatter) : null;
    }
}
