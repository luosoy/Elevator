package com.luosoy.elevator.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luosoy.common.thread.ThreadIdUtil;
import com.luosoy.common.util.UUidUtil;
import com.luosoy.elevator.convert.TestConvert;
import com.luosoy.elevator.dto.TestDTO;
import com.luosoy.elevator.mapper.TestMapper;
import com.luosoy.elevator.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private TestConvert testConvert;

    public PageInfo<TestDTO> queryTest(int pageNum, int pageSize) {
        Page<Test> tests = PageHelper.startPage(pageNum, pageSize);
        testMapper.selectAll();
        return testConvert.testCovert(tests.toPageInfo());
    }


    public void saveTest(TestDTO testDTO){
        Test test = testConvert.dto2Model(testDTO);
        test.setUuid(UUidUtil.generateThreadUUId());
        testMapper.insert(test);
        System.out.println(test);
    }

    public List<TestDTO> querytest(TestDTO testDTO) {
        Condition condition = new Condition(Test.class);
        condition.createCriteria().andEqualTo("name",testDTO.getName());
        List<Test> tests = testMapper.selectByCondition(condition);
        return testConvert.model2DTO(tests);
    }

}
