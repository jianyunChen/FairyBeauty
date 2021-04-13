package com.fairyBeauty.utils;

import com.fairyBeauty.enums.ResponseEnum;
import lombok.Data;

/**
 * 业务错误返回 Exception
 * @Author chenjianyun
 * @Date 2021/3/22 17:17
 * @Description 业务错误返回
 */
@Data
public class WebResponseException extends RuntimeException {
    /**
     * 错误码 默认705
     */
    private String code = ResponseEnum.UN_KNOW_ERROR.getCode();
    /**
     * 错误信息 默认 服务器未知错误
     */
    private String message = ResponseEnum.UN_KNOW_ERROR.getMessage();

    /**
     * 业务报错
     * @param mssage 错误信息
     */
    public WebResponseException(String mssage){
        setCode(code);
        setMessage(mssage);
    }

    /**
     * 业务报错
     * @param code 错误码
     * @param mssage 错误信息
     */
    public WebResponseException(String code,String mssage){
        setCode(code);
        setMessage(mssage);
    }
    /**
     * 业务报错
     * @param code 错误码
     * @param mssage 错误信息
     */
    public WebResponseException(ResponseEnum responseEnum){
        setCode(responseEnum.getCode());
        setMessage(responseEnum.getMessage());
    }
    /**
     * 业务报错
     * @param code 错误码
     * @param mssage 错误信息
     */
    public WebResponseException(ResponseEnum responseEnum,String mssage){
        setCode(responseEnum.getCode());
        setMessage(responseEnum.getMessage() + "," + mssage);
    }
}
