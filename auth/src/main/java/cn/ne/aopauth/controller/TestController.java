package cn.ne.aopauth.controller;


import cn.ne.aopauth.annotation.MonitorRequest;
import cn.ne.aopauth.model.domain.AccountDomain;
import cn.ne.aopauth.utils.resutils.ErrorStatus;
import cn.ne.aopauth.utils.resutils.ResponseUtil;
import cn.ne.aopauth.utils.resutils.SystemConstants;
import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/ip", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public String testRequest(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<>();

        resMap.put("request.getLocalAddr()", request.getLocalAddr());
        resMap.put("request.getRemoteAddr()", request.getRemoteAddr());
        resMap.put("request.getRemoteHost()", request.getRemoteHost());

        System.out.println(
                "request.getLocalAddr()===> " + request.getLocalAddr() + "\n" +
                        "request.getRemoteAddr()===> " + request.getRemoteAddr() + "\n" +
                        "request.getRemoteAddr()===> " + request.getRemoteHost() + "\n"
        );
        System.out.println("ip ==> " + getIpAddress(request));
        return JSON.toJSONString(resMap);
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
