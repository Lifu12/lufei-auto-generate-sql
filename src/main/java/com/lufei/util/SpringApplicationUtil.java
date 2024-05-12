package com.lufei.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author kun.li
 */
@Component
public class SpringApplicationUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringApplicationUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }
}
