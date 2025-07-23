package com.monty.exam.configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
@AutoConfigureBefore({JacksonAutoConfiguration.class})
public class DateTimeAutoConfiguration {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperBuilderCustomizer() {
        return builder -> {
            // Define the format for LocalDateTime
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // Define the format for LocalDate
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Add the custom serializer and deserializer for LocalDateTime
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter))
                    .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));

            // Add the custom serializer and deserializer for LocalDate
            builder.serializerByType(LocalDate.class, new LocalDateSerializer(dateFormatter))
                    .deserializerByType(LocalDate.class, new LocalDateDeserializer(dateFormatter));

            // Enable features like WRITE_DATES_AS_TIMESTAMPS
            builder.featuresToEnable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        };
    }

}
