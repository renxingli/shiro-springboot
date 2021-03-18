package test.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import test.demo.entity.User;
import test.demo.service.Impl.UserServiceImpl;

/**
 * @author A stubborn man
 * @create 2021/3/16 10:14
 * @Description
 */
public class UserRealm extends AuthorizingRealm {


    @Autowired
    private UserServiceImpl userService;
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权->doGetAuthorizationInfo");
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.toString());
        User principal =(User) subject.getPrincipal();
        System.out.println(principal);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission("user:add");
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证->doGetAuthorizationInfo");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(new User()).eq("phone",token.getUsername());
        User one = userService.getOne(queryWrapper);
        System.out.println(one);
        if(one==null){
            return null;
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("rxl",one);
        return new SimpleAuthenticationInfo(one,one.getUpwd(),"");
    }

    @Override
    public void onLogout(PrincipalCollection principals) {
        super.onLogout(principals);
    }
}
