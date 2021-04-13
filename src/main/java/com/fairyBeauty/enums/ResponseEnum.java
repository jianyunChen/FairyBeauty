package com.fairyBeauty.enums;

/**
 * web请求返回状态码
 */
public enum ResponseEnum {
    /** 成功 */
    SUCCESS("200",""),
    /** 业务错误 */
    BUSINESS_ERROR("701","业务错误"),
    /** 查询错误 */
    SELECT_ERROR("702","查询错误"),
    /** 未找到指定数据错误 */
    NOT_FIND("703","未找到指定数据错误"),
    /** 未找到指定数据错误 */
    PRIMARY_KEY_NULL("706","主键丢失"),
    /** 权限错误 */
    ROLE_ERROR("704","对不起,您没有访问权限！"),
    /** 服务器未知错误 */
    UN_KNOW_ERROR("705","服务器未知错误。"),
    /** 服务器未知错误 */
    USER_NULL("801","用户信息不存在或已过期，请重新登录！"),
    /** 服务器未知错误 */
    USER_ROLELIST_NULL("802","用户角色信息不存在或已过期，请重新登录！"),
    /** 服务器未知错误 */
    USER_ERROR("805","账号密码错误，请重新登录！"),
    /** 服务器未知错误 */
    TOKEN_ERROR_USER_NOTEXIST("601","TOKEN与当前用户不符！"),
    /** 服务器未知错误 */
    TOKEN_ERROR_TIMEOUT("602","TOKEN已过期，请重新登录！"),
    /** 服务器未知错误 */
    TOKEN_ERROR_NOT_FIND("603","TOKEN不存在！"),
    ;

    /**
     * 初始化
     * @param code
     * @param message
     */
    ResponseEnum(String code, String message){
        setCode(code);
        setMessage(message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 状态码
     */
    private String code;
    /**
     * 返回信息
     */
    private String message;
}
