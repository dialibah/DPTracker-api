package sn.dialibah.common.config.configuration.converters;

import org.dozer.DozerConverter;

import java.time.LocalDateTime;

/**
 * Created by nureynisow on 17/09/2017.
 * for DPTracker
 */
public class LocalDateConverter extends DozerConverter<LocalDateTime, LocalDateTime> {
    /**
     * {@inheritDoc}
     */
    public LocalDateConverter() {
        super(LocalDateTime.class, LocalDateTime.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime convertTo(LocalDateTime source, LocalDateTime destination) {
        return source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime convertFrom(LocalDateTime source, LocalDateTime destination) {
        return convertTo(source, destination);
    }
}
