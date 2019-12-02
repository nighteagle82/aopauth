package cn.ne.aopauth.controller;


import cn.ne.aopauth.annotation.MonitorRequest;
import cn.ne.aopauth.model.domain.AccountDomain;
import cn.ne.aopauth.utils.resutils.ErrorStatus;
import cn.ne.aopauth.utils.resutils.ResponseUtil;
import cn.ne.aopauth.utils.resutils.SystemConstants;
import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "测试控制器")
@RestController
public class TestController {

    // 需要授权认证
    @MonitorRequest
    @RequestMapping(value = "/add1", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String testAdd(@RequestBody AccountDomain accountDomain) {
        Map<String, Object> resMap = null;

        resMap = ResponseUtil.setResponseMap(SystemConstants.CODE_1, SystemConstants.SUCCESS, new ErrorStatus(
                SystemConstants.E000, SystemConstants.getMessage().get(SystemConstants.E000)
        ), "正常流程");

        return JSON.toJSONString(resMap);
    }

    // 无需授权认证
    @RequestMapping(value = "/add2", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String testAdd2(@RequestBody AccountDomain accountDomain) {
        Map<String, Object> resMap = new HashMap<>();

        resMap = ResponseUtil.setResponseMap(SystemConstants.CODE_1, SystemConstants.SUCCESS, new ErrorStatus(
                SystemConstants.E000, SystemConstants.getMessage().get(SystemConstants.E000)
        ), "正常流程");

        return JSON.toJSONString(resMap);
    }

    // 需要授权认证
    @MonitorRequest
    @RequestMapping(value = "/add3", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String testAdd3(@RequestBody AccountDomain accountDomain) {
        Map<String, Object> resMap = new HashMap<>();

        resMap = ResponseUtil.setResponseMap(SystemConstants.CODE_1, SystemConstants.SUCCESS, new ErrorStatus(
                SystemConstants.E000, SystemConstants.getMessage().get(SystemConstants.E000)
        ), "正常流程");
        return JSON.toJSONString(resMap);
    }

    // 需要授权认证
    @MonitorRequest
    @RequestMapping(value = "/del/{params}", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String testDel(@PathVariable String params) {
        Map<String, Object> resMap = null;
        resMap = ResponseUtil.setResponseMap(SystemConstants.CODE_1, SystemConstants.SUCCESS, new ErrorStatus(
                SystemConstants.E000, SystemConstants.getMessage().get(SystemConstants.E000)
        ), "正常流程");
        return JSON.toJSONString(resMap);
    }


}
