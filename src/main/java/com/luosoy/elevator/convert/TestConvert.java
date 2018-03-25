package com.luosoy.elevator.convert;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.luosoy.elevator.dto.TestDTO;
import com.luosoy.elevator.model.Test;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TestConvert {

    PageInfo<TestDTO> testCovert(PageInfo<Test> testPage);

    Test dto2Model(TestDTO testDTO);

    List<TestDTO> model2DTO(List<Test> tests);

}