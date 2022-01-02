package pers.xds.wtuapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.xds.wtuapp.database.bean.User;
import pers.xds.wtuapp.database.mapper.UserMapper;
import pers.xds.wtuapp.service.UserService;

/**
 * @author DeSen Xu
 * @date 2021-12-22 21:18
 */
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq(UserMapper.COLUMN_USERNAME, username));
    }

}
