package com.luosoy.elevator.facade;

import com.github.pagehelper.PageInfo;
import com.luosoy.elevator.dto.TestDTO;

import java.util.List;

public interface TestFacade {

    public PageInfo<TestDTO> queryTest(int pageNum, int pageSize);

    public void saveTest(TestDTO testDTO);

    public List<TestDTO> querytest(TestDTO testDTO);
}
