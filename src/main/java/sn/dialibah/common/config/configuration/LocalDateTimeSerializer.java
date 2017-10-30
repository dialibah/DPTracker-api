package sn.dialibah.common.config.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author: cpetit.
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

	private static final DateTimeFormatter FORMATTER = DateUtils.ISO_OFFSET_OPTIONAL_FORMATTER;

	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeString(FORMATTER.format(value));
	}
}
