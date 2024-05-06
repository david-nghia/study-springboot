package com.tech.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ExceptionConfiguration {

    @Value(value = "${classpath:message/application-businessExceptionLabels.yml}")
    private String businessMessagePath;

    @Bean
    public MessageSource businessMessage() {
        final ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(this.businessMessagePath);
        messageSource.setAlwaysUseMessageFormat(true);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
