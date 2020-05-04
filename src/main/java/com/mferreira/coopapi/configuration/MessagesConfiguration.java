package com.mferreira.coopapi.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import javax.annotation.PostConstruct;

@Configuration
public class MessagesConfiguration {

    private MessageSource messageSource = null;
    private MessageSourceAccessor accessor;

    public MessagesConfiguration() {  }

    public MessagesConfiguration(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource);
        MessagesComponent.setMessages(this);
    }

    public String get(String code) {
        return accessor.getMessage(code);
    }

    public String get(String code, String... parameters) {
        return accessor.getMessage(code, parameters);
    }
}
