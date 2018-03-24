package com.luosoy.elevator.config.filter;

import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        //配置无需过滤的路径或者静态资源，如：css，imgage等
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ConfigurableSiteMeshFilter());
        registration.addUrlPatterns("/web/*");
        registration.setName("SiteMeshFilter");
        registration.setOrder(1);
        return registration;
    }
}
