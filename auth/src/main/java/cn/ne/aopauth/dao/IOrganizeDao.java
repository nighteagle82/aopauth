package cn.ne.aopauth.dao;


import cn.ne.aopauth.model.pojo.BindOrganize;
import cn.ne.aopauth.model.pojo.Organize;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface IOrganizeDao  {

    @Select(value = "SELECT IFNULL(COUNT(id),0) FROM organize WHERE isDelete=0 AND orgName=#{orgName};")
    public Integer isOrgNameExists(@Param("orgName") String orgName) ;

    @Select(value = "SELECT IFNULL(MAX(orgCode),0) FROM organize WHERE isDelete=0; ")
    public Integer getMaxOrgCode();

    @Insert(value = "INSERT INTO organize ( " +
            "       orgName, orgPwd, orgCode, serctKey, effectDate, createDate, isDelete " +
            "   ) VALUES ( " +
            "       #{organizeMap.orgName}, #{organizeMap.orgPwd}, #{organizeMap.orgCode}, #{organizeMap.serctKey}, " +
            "       DATE_ADD(now(),INTERVAL #{organizeMap.effect} MONTH), NOW(), 0" +
            "   );")
//    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public Long insertOrganize(@Param("organizeMap") Map<String, Object> organizeMap);


    @Select(value = "SELECT " +
            "       id,orgName, orgPwd, orgCode, serctKey, effectDate, createDate, isDelete " +
            "   FROM organize " +
            "   WHERE isDelete=0 AND orgName=#{orgName}")
    public Organize getOrganizeById(@Param("orgName") String orgName);


    @Select(value = "SELECT IFNULL(COUNT(id),0) FROM bind_organize WHERE isDelete=0 AND orgCode=#{orgCode};")
    public Integer isOrgCodeExists(@Param("orgCode") Integer orgCode);

    @Select(value = "SELECT IFNULL(COUNT(id),0) FROM organize WHERE isDelete=0 AND " +
            "orgCode=#{organizeMap.orgCode} AND serctKey=#{organizeMap.serctKey};")
    public Integer isCodeAndSerctExists(@Param("organizeMap") Map<String, Object> organizeMap);

    @Insert(value = "<script> " +
            "   INSERT INTO bind_organize ( " +
            "           orgCode, authUrl, createDate, isDelete " +
            "   ) VALUES " +
            "       <foreach collection='bindOrganizeList' item='item' index='index' separator=','> " +
            "   (" +
            "       #{item.orgCode},#{item.authUrl}, NOW(), 0" +
            "   ) " +
            "       </foreach> " +
            "</script>")
    public Integer insertBindOrganize(@Param("bindOrganizeList") List<BindOrganize> bindOrganizeList);


    @Select(value = "SELECT authUrl FROM bind_organize WHERE orgCode = #{orgCode};")
    public List<String> getBindOrganizeList(@Param("orgCode") Integer orgCode);

}
