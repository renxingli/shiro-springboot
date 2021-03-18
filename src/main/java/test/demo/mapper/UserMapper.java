package test.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import test.demo.entity.User;

/**
 * @author A stubborn man
 * @create 2021/3/16 13:37
 * @Description
 */

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
