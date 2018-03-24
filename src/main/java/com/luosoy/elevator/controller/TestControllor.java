package com.luosoy.elevator.controller;

import com.luosoy.common.web.Response;
import com.luosoy.elevator.dto.TestDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestControllor {

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @PostMapping(value = "test")
    @ResponseBody
    public Response<TestDTO> test(@RequestBody TestDTO test){
        System.out.println(test);
        TestDTO testDTO = new TestDTO();
        testDTO.setName("12312");
        testDTO.setTest("444");
        return Response.success(testDTO);
    }
}
