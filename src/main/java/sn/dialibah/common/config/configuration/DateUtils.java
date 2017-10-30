package sn.dialibah.common.config.configuration;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * Author: gcoppens.
 */
public class DateUtils {

	public static final DateTimeFormatter ISO_OFFSET_OPTIONAL_FORMATTER =
		new DateTimeFormatterBuilder()
				.parseCaseInsensitive()
				.append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
				.optionalStart()
				.appendOffsetId()
				.toFormatter();
}
