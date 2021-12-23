package pers.xds.wtuapp.database.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DeSen Xu
 * @date 2021-10-31 21:00
 */
@Data
@TableName("t_user")
@NoArgsConstructor
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField
    private String username;

    @TableField
    private String validation;

    @TableField
    private String role;

    public User(String username, String validation) {
        this.username = username;
        this.validation = validation;
    }
}
