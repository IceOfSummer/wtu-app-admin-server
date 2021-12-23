package pers.xds.wtuapp.service;

import pers.xds.wtuapp.database.bean.User;

/**
 * @author DeSen Xu
 * @date 2021-12-22 21:18
 */
public interface UserService {

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户信息
     */
    User getUserByUsername(String username);
}
