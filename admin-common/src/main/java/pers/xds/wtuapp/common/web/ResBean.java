package pers.xds.wtuapp.common.web;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 标准响应对象
 * @author DeSen Xu
 * @date 2021-11-14 22:04
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResBean<T> {

    private static int successCode = 0;

    private static int failCode = 1;

    private static String successMessage = "success";

    private static String failMessage = "fail";

    public static final String EMPTY_RESPONSE = "";

    /**
     * 返回code码，20000成功，其他失败
     */
    private int code;

    /**
     * 对code的描述
     */
    private String message;

    /**
     * 返回体
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ResBean(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResBean(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    /**
     * 设置请求成功默认的code值
     * @param successCode 请求成功的响应code值
     */
    public static void setSuccessCode(int successCode) {
        ResBean.successCode = successCode;
    }

    /**
     * 设置请求失败后默认的code值
     * @param failCode 请求失败的响应code值
     */
    public static void setFailCode(int failCode) {
        ResBean.failCode = failCode;
    }

    /**
     * 设置请求成功后的message
     * @param successMessage 请求成功后的message
     */
    public static void setSuccessMessage(String successMessage) {
        ResBean.successMessage = successMessage;
    }

    /**
     * 设置请求失败后的message
     * @param failMessage 请求失败后的message
     */
    public static void setFailMessage(String failMessage) {
        ResBean.failMessage = failMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResBean<T> success() {
        return new ResBean<>(successCode, successMessage);
    }

    public static <T> ResBean<T> success(T data) {
        return new ResBean<>(successCode, successMessage, data);
    }

    public static <T> ResBean<T> fail(String errorMsg) {
        return new ResBean<>(failCode, errorMsg);
    }

    public static <T> ResBean<T> fail(ResBeanCode code) {
        return new ResBean<>(code.getCode(), code.getMessage());
    }

    public static <T> ResBean<T> fail(ResBeanCode code, String message) {
        return new ResBean<>(code.getCode(), message);
    }


}
