package sn.dialibah.common.config.configuration;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sn.dialibah.chargements.models.Chargement;
import sn.dialibah.common.config.configuration.converters.DozerBeanMappingHelper;
import sn.dialibah.common.config.configuration.converters.LocalDateConverter;
import sn.dialibah.user.model.ActivationToken;

/**
 * Created by nureynisow on 17/09/2017.
 * for DPTracker
 */

@Configuration
public class DozerConfiguation {
    @Bean
    public DozerBeanMapper getDozerBeanMapper() {
        final DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(DozerBeanMappingHelper.buildClassMapper(ActivationToken.class, ActivationToken.class, "expirationDateTime", LocalDateConverter.class));
        mapper.addMapping(DozerBeanMappingHelper.buildClassMapper(Chargement.class, Chargement.class, "leavingDate", LocalDateConverter.class));
        mapper.addMapping(DozerBeanMappingHelper.buildClassMapper(Chargement.class, Chargement.class, "arrivalDate", LocalDateConverter.class));
        return mapper;
    }
}
