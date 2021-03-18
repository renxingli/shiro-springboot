package test.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import test.config.Febs;

/**
 * @author A stubborn man
 * @create 2021/3/16 10:06
 * @Description
 */

@Controller
public class testController {

    @RequestMapping({"/","/index"})
    public String index(Model model){
        model.addAttribute("msg","hello,shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/user/login")
    public String toLogin(){
        Febs febs = new Febs();
        return "login";
    }

    @PostMapping("/login")
    public String login(String username,String password,Model model){
        System.err.println(username);
        System.err.println(password);
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //登录验证
        try {
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("mag","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("mag","密码错误");
            return "login";
        }
    }
//    @RequestMapping("/logout")
//    public String logout(){
//        System.err.println("1");
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
//        return "redirect:index";
//    }

    @RequestMapping("/noauth")
    @ResponseBody
    @RequiresPermissions("view:add")
    public String Unauthorized(){
        return "权限不足";
    }
}
