package cn.ne.aopauth.service.auth.imple;


import cn.ne.aopauth.dao.IOrganizeDao;
import cn.ne.aopauth.model.domain.OrganizeDomain;
import cn.ne.aopauth.model.pojo.BindOrganize;
import cn.ne.aopauth.model.pojo.Organize;
import cn.ne.aopauth.service.auth.IAuthService;
import cn.ne.aopauth.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthServiceImple implements IAuthService {

    @Autowired
    private IOrganizeDao organizeDao;

    @Override
    public Integer isOrgNameExists(String orgName) {
        return this.organizeDao.isOrgNameExists(orgName);
    }

    @Override
    public Organize insertOrganize(OrganizeDomain organizeDomain) {
        long id = 0;
        Organize organize = new Organize();
        Map<String, Object> organizeMap = new HashMap<>();
        if (organizeDomain != null) {

            int orgCode = this.organizeDao.getMaxOrgCode();
            orgCode = (orgCode == 0) ? 10000 : (orgCode + 1); // 如果没有找到，从0开始，否则将最大值自增1.
//            secretKey生成规则 = MD5(orgCode+UUID+加盐)【取orgName为盐值】
            String uuidCode = UUID.randomUUID().toString().replaceAll("-", "");
            String serctKey = MD5.toMD5(orgCode + uuidCode + organizeDomain.getOrgName(), 32);

            organizeMap.put("orgName", organizeDomain.getOrgName());
            organizeMap.put("orgPwd", MD5.toMD5(organizeDomain.getOrgPwd(), 32));
            organizeMap.put("orgCode", orgCode);
            organizeMap.put("serctKey", serctKey);
            organizeMap.put("effect", 6);   // 默认有效期6个月

            id = this.organizeDao.insertOrganize(organizeMap);
            if (id != 0) {
                organize = this.organizeDao.getOrganizeById(organizeDomain.getOrgName());
            }
        }
        return organize;
    }


    @Override
    public Integer isOrgCodeExists(Integer orgCode) {
        return this.organizeDao.isOrgCodeExists(orgCode);
    }

    @Override
    public Integer isCodeAndSerctExists(OrganizeDomain organizeDomain) {
        if (organizeDomain != null) {
            Map<String,Object> organizeMap = new HashMap<>();
            organizeMap.put("orgCode",organizeDomain.getOrgCode());
            organizeMap.put("serctKey",organizeDomain.getSerctKey());
            return this.organizeDao.isCodeAndSerctExists(organizeMap);
        }
        return null;
    }

    @Override
    public Integer insertBindOrganize(List<BindOrganize> bindOrganizeList) {
        if (bindOrganizeList != null && bindOrganizeList.size() > 0) {
            return this.organizeDao.insertBindOrganize(bindOrganizeList);
        }
        return null;
    }

    @Override
    public List<String> getBindOrganizeList(Integer orgCode) {
        return this.organizeDao.getBindOrganizeList(orgCode);
    }


}
