package es.masmovil.phoneorder.config;

import com.github.mongobee.Mongobee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongobeeConfig {

    private static final String SCAN_PACKAGE = "es.masmovil.phoneorder.config.changelogs";

    @Value("${spring.data.mongodb.uri}")
    private String mongoUrl;

    @Bean
    public Mongobee mongobee(final MongoTemplate mongoTemplate) {
        return new Mongobee(mongoUrl)
                .setMongoTemplate(mongoTemplate)
                .setChangeLogsScanPackage(SCAN_PACKAGE);
    }
}