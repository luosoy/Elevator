package com.luosoy.elevator.controller;

import com.luosoy.common.web.Response;
import com.luosoy.elevator.convert.TestConvert;
import com.luosoy.elevator.dto.Test1DTO;
import com.luosoy.elevator.dto.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/test")
public class TestControllor {

    @Autowired
    private TestConvert testConvert;

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @PostMapping(value = "test")
    @ResponseBody
    public Response<Test1DTO> test(@RequestBody TestDTO test){
        System.out.println(test);
        Test1DTO test1DTO = testConvert.testConvert(test);
        return Response.success(test1DTO);
    }
}
