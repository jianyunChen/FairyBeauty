package com.fairyBeauty.enums;

/**
 * 操作类型
 */
public enum TokenExistEnum {
    SUCCESS(0,"成功"),
    ERROR(1,"失败"),
    REST(2,"刷新"),
    ;

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    /**
     * 编码
     */
    private Integer code;
    /**
     * 名称
     */
    private String name;
    TokenExistEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
