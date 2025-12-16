package org.threepixeldev.saungeraadmin.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
@PropertySource(value = "classpath:messages.properties", encoding = "UTF-8")
public class AppConfig {
}
