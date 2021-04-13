package com.fairyBeauty.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fairyBeauty.enums.ActionTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 
 * </p>
 *
 * @author s
 * @since 2021-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_action")
@ApiModel(value="TAction对象", description="")
public class TAction implements Serializable {

    public TAction(){
    }

    public TAction(String actionName,ActionTypeEnum actionTypeEnum,String actionUserId,String actionUserName,Long beginTime,String message,String ip){
        actionTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        setActionName(actionName);
        setActionType(Integer.parseInt(actionTypeEnum.getCode()));
        setActionTypeName(actionTypeEnum.getName());
        setActionUserId(actionUserId);
        setActionUserName(actionUserName);
        if(ActionTypeEnum.ACTION_INFO.equals(actionTypeEnum)){
            setMessage("账号为【"+actionUserId+"】的用户于在IP为【"+ip+"】的客户端调用了接口【"+actionName+"】");
        }else{
            setMessage(message);
        }

        setIp(ip);
    }

    public TAction(String actionName,Integer actionType,String actionTypeName,String actionUserId,String actionUserName,Long beginTime,String message,String ip){
        actionTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        setActionName(actionName);
        setActionType(actionType);
        setActionTypeName(actionTypeName);
        setActionUserId(actionUserId);
        setActionUserName(actionUserName);
        if(ActionTypeEnum.ACTION_INFO.getCode().equals(actionType.toString())){
            setMessage("账号为【"+actionUserId+"】的用户于在IP为【"+ip+"】的客户端调用了接口【"+actionName+"】");
        }else{
            setMessage(message);
        }

        setIp(ip);
    }

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "模块类型：basic oam oomm")
    private String modularType;

    @ApiModelProperty(value = "操作方法名称")
    private String actionName;

    @ApiModelProperty(value = "操作类型：0登录日志 1操作日志 2错误日志 3调试日志 4监控日志")
    private Integer actionType;

    @ApiModelProperty(value = "操作类型名称")
    private String actionTypeName;

    @ApiModelProperty(value = "操作时间")
    private String actionTime;

    @ApiModelProperty(value = "操作人编号")
    private String actionUserId;

    @ApiModelProperty(value = "操作人名称")
    private String actionUserName;

    @ApiModelProperty(value = "操作耗时")
    private Integer actionTimeConsuming;

    @ApiModelProperty(value = "错误信息")
    private String message;

    @ApiModelProperty(value = "访问ip地址")
    private String ip;


}
