package com.luosoy.elevator.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
public class Test {
    @Id
    private String uuid;

    private Integer age;

    private String name;

    private Date date;
}