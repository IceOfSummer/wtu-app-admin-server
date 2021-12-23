package pers.xds.wtuapp.common.security.role;

/**
 * @author DeSen Xu
 * @date 2021-12-22 12:13
 */
public enum Roles {
    /**
     * 普通用户
     */
    NORMAL_USER("NORMAL_USER"),
    /**
     * 管理员
     */
    ADMIN("ADMIN");

    private final String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
