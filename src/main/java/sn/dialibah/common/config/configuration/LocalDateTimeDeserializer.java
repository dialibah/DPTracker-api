package sn.dialibah.common.config.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author: cpetit.
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

	private static final DateTimeFormatter FORMATTER = DateUtils.ISO_OFFSET_OPTIONAL_FORMATTER;

	@Override
	public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
		switch (parser.getCurrentToken()) {
			case VALUE_STRING:
				String rawDate = parser.getText();
				return FORMATTER.parse(rawDate, LocalDateTime::from);
			case START_ARRAY:
				parser.getCodec().readTree(parser);
		}
		throw context.wrongTokenException(parser, JsonToken.START_ARRAY, "Expected string.");
	}
}
