package com.luosoy.elevator.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.luosoy.common.web.Response;
import com.luosoy.elevator.convert.TestConvert;
import com.luosoy.elevator.dto.TestDTO;
import com.luosoy.elevator.facade.TestFacade;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestControllor {

    @Autowired
    private TestFacade tf;

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @PostMapping(value = "/test")
    @ResponseBody
    public Response<PageInfo<TestDTO>> test(@RequestBody TestDTO test){
        System.out.println(test);
        PageInfo<TestDTO> testDTOPageInfo = tf.queryTest(1, 100);
        return Response.success(testDTOPageInfo);
    }

    @PostMapping(value = "/savetest")
    @ResponseBody
    public Response<String> saveTest(@RequestBody TestDTO testDTO){
        tf.saveTest(testDTO);
        return Response.success("保存成功");
    }

    @PostMapping(value = "/querytest")
    @ResponseBody
    public Response<List<TestDTO>> querytest(@RequestBody TestDTO testDTO){
        List<TestDTO>  testDTOList= tf.querytest(testDTO);
        return Response.success(testDTOList);
    }


    /**
     * 这个方法需要TOKEN
     * @param testDTO
     * @return
     */
    @PostMapping(value = "/queryWithToken")
    @ResponseBody
    @RequiresAuthentication
    public Response<List<TestDTO>> queryWithToken(@RequestBody TestDTO testDTO){
        List<TestDTO>  testDTOList= tf.querytest(testDTO);
        return Response.success(testDTOList);
    }
}
