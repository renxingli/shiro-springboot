package test.demo.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import test.demo.entity.User;
import test.demo.mapper.UserMapper;
import test.demo.service.UserService;

/**
 * @author A stubborn man
 * @create 2021/3/16 13:39
 * @Description
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
