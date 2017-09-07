package sn.dialibah.common.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by nureynisow on 15/08/2017.
 * for DPTracker
 */

@Configuration
@EnableMongoRepositories("sn.dialibah")
public class MongoConfig {
}
