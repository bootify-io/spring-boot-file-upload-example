package io.bootify.file_upload.config;

import io.bootify.file_upload.util.Jackson3JsonFormatMapper;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.hibernate.autoconfigure.HibernatePropertiesCustomizer;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tools.jackson.databind.ObjectMapper;


@Configuration
@EntityScan("io.bootify.file_upload")
@EnableJpaRepositories("io.bootify.file_upload")
@EnableTransactionManagement
public class DomainConfig {

    @Bean
    public HibernatePropertiesCustomizer jsonFormatMapper(final ObjectMapper objectMapper) {
        return properties -> properties.put(AvailableSettings.JSON_FORMAT_MAPPER, new Jackson3JsonFormatMapper(objectMapper));
    }

}
