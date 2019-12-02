package cn.ne.aopauth.config;


import cn.ne.aopauth.aop.AopHandler;
import cn.ne.aopauth.security.TokenHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class BeanConfig {

    // 配置properties属性注入
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }

    @Bean
    public TokenHandler tokenHandler(){
        return new TokenHandler();
    }

    @Bean
    public AopHandler aopHandler(){
        return new AopHandler();
    }

}
