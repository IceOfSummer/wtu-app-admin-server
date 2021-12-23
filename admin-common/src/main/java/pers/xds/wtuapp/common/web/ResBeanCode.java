package pers.xds.wtuapp.common.web;

/**
 * @author DeSen Xu
 * @date 2021-12-18 16:35
 */
public enum ResBeanCode {
    /**
     * 教务系统登录过期
     */
    LOGIN_FAILED(100, "登录失败"),
    AUTHORIZATION_NEEDED(101, "请先登录")
    ;


    private final int code;
    private final String message;

    ResBeanCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
