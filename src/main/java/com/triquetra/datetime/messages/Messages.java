package com.triquetra.datetime.messages;

/**
 * Project: spring-jquery-timefield
 * Date: 4/11/17
 * Created by: sayef
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;


/**
 * Helper to simplify accessing i18n messages in code
 * This finds messages automatically found from src/main/resources (files named messages_*.properties)
 * This example uses hard-coded English locale.
 */
@Component
public class Messages {

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
    }

    public String get(String code) {
        return accessor.getMessage(code);
    }

}
