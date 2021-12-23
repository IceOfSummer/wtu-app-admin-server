package pers.xds.wtuapp.security.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pers.xds.wtuapp.common.security.role.UserRole;
import pers.xds.wtuapp.database.bean.User;
import pers.xds.wtuapp.service.UserService;

/**
 * @author DeSen Xu
 * @date 2021-12-22 21:23
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    
    private final UserService userService;

    public UserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("can not find user: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getValidation(), UserRole.parseRoles(user.getRole()));
    }
    
}
