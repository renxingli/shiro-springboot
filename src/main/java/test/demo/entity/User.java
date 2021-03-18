package test.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author A stubborn man
 * @create 2021/3/16 13:35
 * @Description
 */

@Data
@TableName("user")
public class User {

    @TableId(value = "uid",type = IdType.AUTO)
    private Integer uid;

    @TableField("phone")
    private String phone;

    @TableField("upwd")
    private String upwd;
}
