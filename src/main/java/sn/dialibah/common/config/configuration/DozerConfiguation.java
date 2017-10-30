package sn.dialibah.common.config.configuration;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * Created by nureynisow on 17/09/2017.
 * for DPTracker
 */

@Configuration
public class DozerConfiguation {
    @Bean
    public DozerBeanMapper getDozerBeanMapper() {
        return new DozerBeanMapper(Collections.singletonList("dozer/global.xml"));
    }
}
