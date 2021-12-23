package pers.xds.wtuapp.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.xds.wtuapp.database.bean.User;

/**
 * @author DeSen Xu
 * @date 2021-10-31 20:52
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    String COLUMN_ID = "id";
    String COLUMN_USERNAME = "username";
    String COLUMN_VALIDATION = "validation";
    String COLUMN_SESSION_CODE = "session_code";

}
