package com.luosoy.elevator.convert;

import com.luosoy.elevator.dto.Test1DTO;
import com.luosoy.elevator.dto.TestDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TestConvert {

    List<Test1DTO> testConvert(List<TestDTO> test1List);

    Test1DTO testConvert(TestDTO test1);

}