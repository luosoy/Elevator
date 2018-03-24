package com.luosoy.common.util;

import org.springframework.context.ApplicationContext;

/**
 * 描述: Spring上下文工具类
 * 版权: 税友软件集团股份有限公司
 * 日期: 2018/1/29
 * 作者: jsh
 */
public class SpringContextUtil {
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name,Class<T> clazz){
        return applicationContext.getBean(name, clazz);
    }
}
