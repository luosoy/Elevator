package com.luosoy.elevator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(value = {"com.luosoy"})
@MapperScan(basePackages={"com.luosoy.elevator.mapper"})
public class ElevatorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElevatorServiceApplication.class, args);
    }
}
