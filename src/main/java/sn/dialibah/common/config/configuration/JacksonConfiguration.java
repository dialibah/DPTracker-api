package sn.dialibah.common.config.configuration;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Author: klabesse.
 */
@Configuration
public class JacksonConfiguration {

	@Bean
	Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
		return new Jackson2ObjectMapperBuilder()
				.featuresToDisable(SerializationFeature.WRITE_NULL_MAP_VALUES)
				.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer())
				.serializerByType(LocalDate.class, new LocalDateSerializer())
				.deserializerByType(LocalDate.class, new LocalDateDeserializer())
				.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer());
	}
}