package com.fairyBeauty.enums;

/**
 * 操作类型
 */
public enum ActionTypeEnum {
    LOGIN_INFO("0","登录日志"),
    ACTION_INFO("1","操作日志"),
    ERROR_INFO("2","错误日志"),
    DEBUG_INFO("3","调试日志"),
    SERVICE_INFO("4","服务器日志"),

    LOGIN_IN("0","登入操作"),
    LOGIN_OUT("0","登出操作"),

    OOMM("OOMM", "工单模块"),
    OAM("OAM", "告警模块"),
    BASIC("BASIC", "站点概览")
    ;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    ActionTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
