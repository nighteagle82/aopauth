本DEMO基于本人所设计的云服务中一个OPEN-API中的自定义授权模组修改而来。
出于本对公司项目的保密，所以不能将整个完成的工程直接放出来。
但可以将整个流程的实现原理与基础演示的流程做为一个DEMO公开放布。

更多的细节需要cn.ne.aopauth.security这个包中，来实现授权认证。
为了模拟线上正式产品的同样功能，在演示DEMO中简化了验证的过程。
现阶段只是演示使用AOP+Annotation实现自定义的授权认证。而所谓的权限认证部分也仅仅只是简单的从Redis中取出KV做个简单的效验。

所以在实际产品中，本人在设计TokenHandle这个功能类时，还可以结合spring security对授权路径做了更细粒度的划分。
如果你能理解本DEMO的原理与思想，你也可以使用shiro或者jwt等其他框架进行更多的安全校验检查。以防止人为手工的验证不严谨从而在实际生产过程中出现漏洞。


除此之外，基于本DEMO的原理，还可以实现基于自定义annotation与aop实现例如防止表单重复提交、日志输出等更多功能。
这些功能的原理与本次演示的授权验证其实是一样的，只是业务处理的场景所不同。
之后在使用时只需在指定的方法上使用一个注解，即可实现对控制器或者业务的方法上拦截、日志输出等众多功能。

还是那句话，只要理解了本DEMO的原理，可以做出更多的扩展功能。


Author: NightEagle
Date: 2019/12/1
Mail: chris01@163.com

=======================================================================================
1、注册帐号【POST】
http://localhost:8080/regOrg
{
  "orgName": "test",
  "orgPwd": "test1234"
}

2、绑写帐号可访问的URL地址【POST】
http://localhost:8080/bindOrgAuth
{
  "authUrl": [  "/add1","/add2","/del"],
  "orgCode": 10000
}

3、创建token【POST】
http://localhost:8080/createToken
{
  "orgCode": 10000,
  "serctKey":"843f8a0567a3b74ba3e49bfa2eaf73d4"
}
