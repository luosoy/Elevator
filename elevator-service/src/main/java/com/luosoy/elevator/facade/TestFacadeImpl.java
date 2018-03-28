package com.luosoy.elevator.facade;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.luosoy.elevator.dto.TestDTO;
import com.luosoy.elevator.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestFacadeImpl implements TestFacade  {
    @Autowired
    private TestService testService;

    public PageInfo<TestDTO> queryTest(int pageNum, int pageSize) {
        return testService.queryTest(pageNum,pageSize);
    }

    @Transactional
    public void saveTest(TestDTO testDTO){
        testService.saveTest(testDTO);
    }

    public List<TestDTO> querytest(TestDTO testDTO) {
        return testService.querytest(testDTO);
    }
}
