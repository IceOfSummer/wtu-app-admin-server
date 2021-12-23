package pers.xds.wtuapp.common.security.role;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DeSen Xu
 * @date 2021-12-22 12:13
 */
public class UserRole implements GrantedAuthority {

    private final String role;

    /**
     * 权限前缀
     * 用于SpringSecurity验证
     */
    public static final String ROLE_PREFIX = "ROLE_";

    public UserRole(Roles roles) {
        this.role = roles.getRole();
    }

    /**
     * 创建一个用户
     * @param role 必须是指定的字符串 @see {@link Roles}
     */
    public UserRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return ROLE_PREFIX + role;
    }

    public static List<UserRole> parseRoles(String rolesStr) {
        String[] roles = rolesStr.split(",");

        ArrayList<UserRole> userRoles = new ArrayList<>(roles.length);

        for (String role : roles) {
            if (role.length() != 0) {
                // 防止出现空字符串
                userRoles.add(new UserRole(role));
            }
        }
        return userRoles;
    }

}
