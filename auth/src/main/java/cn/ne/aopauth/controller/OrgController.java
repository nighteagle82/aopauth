package cn.ne.aopauth.controller;

import cn.ne.aopauth.model.domain.BindOrganizeDomain;
import cn.ne.aopauth.model.domain.OrganizeDomain;
import cn.ne.aopauth.model.pojo.BindOrganize;
import cn.ne.aopauth.model.pojo.Organize;
import cn.ne.aopauth.security.TokenHandler;
import cn.ne.aopauth.service.auth.IAuthService;
import cn.ne.aopauth.utils.resutils.ErrorStatus;
import cn.ne.aopauth.utils.resutils.ResponseUtil;
import cn.ne.aopauth.utils.resutils.SystemConstants;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.IntStream.range;

/**
 * @author gl
 */
@Api(tags = "授权控制器")
@RestController
public class OrgController {

    @Autowired
    private IAuthService authService;

    @Autowired
    private TokenHandler tokenHandler;

    // 注册帐号
    @RequestMapping(value = "/regOrg", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String regOrg(@RequestBody OrganizeDomain organizeDomain) {
        Map<String, Object> msgMap = new HashMap<>();
        //TODO：暂不考虑表单验证
        if (organizeDomain != null) {
            if (this.authService.isOrgNameExists(organizeDomain.getOrgName()) > 0) {
                msgMap = ResponseUtil.setResponseMap(SystemConstants.CODE_1, SystemConstants.FAILURE, new ErrorStatus(
                        SystemConstants.E001, SystemConstants.getMessage().get(SystemConstants.E001) + "orgName已存在。"
                ), null);
            } else {
                Organize organize = this.authService.insertOrganize(organizeDomain);
                if (organize != null) {
                    organizeDomain.setOrgName(organize.getOrgName());
                    organizeDomain.setOrgCode(organize.getOrgCode());
                    organizeDomain.setSerctKey(organize.getSerctKey());
                    organizeDomain.setOrgPwd("");
                }
                msgMap = ResponseUtil.setResponseMap(SystemConstants.CODE_0, SystemConstants.SUCCESS, new ErrorStatus(
                        SystemConstants.E000, SystemConstants.getMessage().get(SystemConstants.E000)
                ), organizeDomain);
            }
        }
        return JSON.toJSONString(msgMap);
    }

    // 绑定帐号与授权URL
    @RequestMapping(value = "/bindOrgAuth", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String bindOrgAuth(@RequestBody BindOrganizeDomain bindOrganizeDomain) {
        Map<String, Object> msgMap = new HashMap<>();
        int bindRows = 0;
        //TODO: 暂不考虑表单验证

        if (bindOrganizeDomain != null && bindOrganizeDomain.getAuthUrl().size() > 0) {
            List<BindOrganize> bindOrganizeList = new ArrayList<>();
            if (this.authService.isOrgCodeExists(bindOrganizeDomain.getOrgCode()) > 0) {
                msgMap = ResponseUtil.setResponseMap(SystemConstants.CODE_1, SystemConstants.FAILURE, new ErrorStatus(
                        SystemConstants.E001, SystemConstants.getMessage().get(SystemConstants.E001) + "授权编号不正确或授权接口已绑定。"
                ), null);
            } else {
                if (bindOrganizeDomain.getAuthUrl() != null && bindOrganizeDomain.getAuthUrl().size() > 0) {
                    List<String> authUrl = bindOrganizeDomain.getAuthUrl();
                    range(0, authUrl.size()).forEach(i -> {
                        BindOrganize bindOrganize = new BindOrganize();
                        bindOrganize.setOrgCode(bindOrganizeDomain.getOrgCode());
                        bindOrganize.setAuthUrl(bindOrganizeDomain.getAuthUrl().get(i));
                        bindOrganizeList.add(bindOrganize);
                    });
                    bindRows = authService.insertBindOrganize(bindOrganizeList);
                    if (bindRows == bindOrganizeDomain.getAuthUrl().size()) {
                        msgMap = ResponseUtil.setResponseMap(SystemConstants.CODE_0, SystemConstants.SUCCESS, new ErrorStatus(
                                SystemConstants.E000, SystemConstants.getMessage().get(SystemConstants.E000)
                        ), null);
                    } else {
                        msgMap = ResponseUtil.setResponseMap(SystemConstants.CODE_1, SystemConstants.FAILURE, new ErrorStatus(
                                SystemConstants.E001, SystemConstants.getMessage().get(SystemConstants.E001) + "授权绑定失败。"
                        ), null);
                    }
                }
            }
        }
        return JSON.toJSONString(msgMap);
    }

    @RequestMapping(value = "/createToken", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String createToken(@RequestBody OrganizeDomain organizeDomain) {
        Map<String, Object> msgMap = new HashMap<>();
        if (organizeDomain != null) {
            String token = tokenHandler.createToken(organizeDomain);
            if (!StringUtils.isEmpty(token)) {
                if (token.equalsIgnoreCase("error")) {
                    msgMap = ResponseUtil.setResponseMap(SystemConstants.CODE_1, SystemConstants.FAILURE, new ErrorStatus(
                            SystemConstants.E001, SystemConstants.getMessage().get(SystemConstants.E001) +
                            "orgCode与serctKey不匹配。"
                    ), null);
                } else {
                    Map<String, String> accessToken = new HashMap<>();
                    accessToken.put("token", token);
                    msgMap = ResponseUtil.setResponseMap(SystemConstants.CODE_0, SystemConstants.SUCCESS, new ErrorStatus(
                            SystemConstants.E000, SystemConstants.getMessage().get(SystemConstants.E000)
                    ), accessToken);
                }
            }
        }
        return JSON.toJSONString(msgMap);
    }


}
