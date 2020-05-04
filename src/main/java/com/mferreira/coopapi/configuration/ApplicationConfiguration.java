package com.mferreira.coopapi.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfiguration {

    public static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class.getName());

    @Value("${spring.datasource.url}")
    private String datasourceURL;

    @Bean
    public DataSource getDataSource()
    {
        LOGGER.info("datasource url: " + datasourceURL);
        return DataSourceBuilder.create()
                .url(datasourceURL).build();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
