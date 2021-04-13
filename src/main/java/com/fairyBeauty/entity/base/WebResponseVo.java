package com.fairyBeauty.entity.base;

import com.fairyBeauty.enums.ResponseEnum;
import com.fairyBeauty.utils.WebResponseException;
import lombok.Data;

import java.io.Serializable;

/**
 * web请求返回信息对象 默认成功
 * @Author chenjianyun
 * @Date 2021/3/22 16:56
 * @Description web返回对象
 */
@Data
public class WebResponseVo implements Serializable {
    /**
     * 返回成功对象
     */
    public WebResponseVo(){
    }
    /**
     * 返回成功对象
     * @param data 返回对象
     */
    public WebResponseVo(Object data){
        setData(data);
    }

    /**
     * 返回指定对象
     * @param code 状态码
     * @param message 返回信息
     * @param data 返回对象
     */
    public WebResponseVo(String code, String message, Object data){
        setCode(code);
        setMessage(message);
        setData(data);
    }

    /**
     * 返回错误对象
     * @param code 状态码
     * @param message 返回信息
     */
    public WebResponseVo(String code, String message){
        setCode(code);
        setMessage(message);
    }

    /**
     * 返回错误对象
     * @param responseEnum WebResponse枚举对象
     */
    public WebResponseVo(ResponseEnum responseEnum){
        setCode(responseEnum.getCode());
        setMessage(responseEnum.getMessage());
    }
    /**
     * 返回错误对象
     * @param responseEnum WebResponse枚举对象
     * @param message 自定义错误信息
     */
    public WebResponseVo(ResponseEnum responseEnum, String message){
        setCode(responseEnum.getCode());
        setMessage(responseEnum.getMessage()+ "," + message);
    }
    /**
     * 返回错误对象
     * @param exception 业务报错
     */
    public WebResponseVo(WebResponseException exception){
        setCode(exception.getCode());
        setMessage(exception.getMessage());
    }
    /**
     * 返回错误对象
     * @param exception 业务报错
     * @param message 自定义错误信息
     */
    public WebResponseVo(WebResponseException exception, String message){
        setCode(exception.getCode());
        setMessage(exception.getMessage()+ "," + message);
    }

    /**
     * 状态码
     */
    private String code = "200";
    /**
     * 返回信息
     */
    private String message = "";
    /**
     * 返回对象
     */
    private Object data = null;
    /**
     * 版本号
     */
    private String version = "1.0";
}
