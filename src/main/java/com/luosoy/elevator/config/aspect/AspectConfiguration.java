package com.luosoy.elevator.config.aspect;

import com.luosoy.common.log.LogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AspectConfiguration {

    @Bean
    public LogAspect getLogAspect() {
        return new LogAspect();
    }
}
