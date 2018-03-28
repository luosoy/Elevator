package com.luosoy.elevator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(value = {"com.luosoy"})
public class ElevatorWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElevatorWebApplication.class, args);
    }
}
