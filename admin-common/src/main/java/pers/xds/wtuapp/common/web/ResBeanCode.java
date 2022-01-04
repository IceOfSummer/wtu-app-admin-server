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
    AUTHORIZATION_NEEDED(101, "请先登录"),
    BAD_CREDENTIALS(102, "密码错误"),
    BAD_REQUEST(103, "缺少所必要的参数", 400),
    NO_DATA(104, "暂无相关数据"),
    CAN_NOT_FIND_FILE(105, "无法找到目标文件"),
    ;


    private final int code;
    private final String message;
    private final int httpCode;

    ResBeanCode(int code, String message) {
        this(code, message, 200);
    }

    ResBeanCode(int code, String message, int httpCode) {
        this.code = code;
        this.message = message;
        this.httpCode = httpCode;
    }

    public int getCode() {
        return code;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getMessage() {
        return message;
    }
}
