package cn.ne.aopauth.service.auth;



import cn.ne.aopauth.model.domain.OrganizeDomain;
import cn.ne.aopauth.model.pojo.BindOrganize;
import cn.ne.aopauth.model.pojo.Organize;

import java.util.List;

public interface IAuthService {

    public Integer isOrgNameExists(String orgName) ;

    public Organize insertOrganize(OrganizeDomain organizeDomain);

    public Integer isOrgCodeExists(Integer orgCode);

    public Integer isCodeAndSerctExists(OrganizeDomain organizeDomain);

    public Integer insertBindOrganize(List<BindOrganize> bindOrganizeList);

    public List<String> getBindOrganizeList(Integer orgCode);

}
