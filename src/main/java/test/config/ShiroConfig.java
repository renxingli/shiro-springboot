package test.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author A stubborn man
 * @create 2021/3/16 10:13
 * @Description
 */

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean 3
    @Bean
    public ShiroFilterFactoryBean getshiroFilterFactoryBean(@Qualifier("dwsm") DefaultWebSecurityManager defaultWebSecurityManager,@Lazy Febs febs){
        ShiroProperties shiro = febs.getShiro();
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro的内置过滤器
        /**
         * anon:无需认证就可以访问
         * authc:必须认证了才能让问
         *user:必须拥有记住我功能才能用
         * perms:拥有对某个资源的权限才能访问;
         * role:拥有某个角色权限才能访问
         * */
        Map<String,String> filter = new LinkedHashMap<>();
        filter.put("/user/**","authc");
//        filter.put("/user/add","authc");
//        filter.put("/user/update","authc");
        //权限配置
        filter.put("/user/add","perms[user:add]");
        filter.put("/user/update","perms[user:update]");
        filter.put(shiro.getLogoutUrl(),"logout");
        bean.setFilterChainDefinitionMap(filter);
        bean.setLoginUrl(shiro.getLoginUrl());
        //无权限页面
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }

    //DefaultWebSecurityManager:2
    @Bean(name = "dwsm")
    public DefaultWebSecurityManager getdefaultWebSecurityManage(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }
    //创建realm对象:1
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //thymeleaf整合
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
    
}
