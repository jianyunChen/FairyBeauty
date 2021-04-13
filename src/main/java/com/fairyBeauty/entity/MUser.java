package com.fairyBeauty.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_user")
@ApiModel(value="MUser对象", description="用户表")
public class MUser extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编号")
    private String userId;

    @ApiModelProperty(value = "用户账号")
    private String userCode;

    @ApiModelProperty(value = "用户密码")
    private String userPassword;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "用户电话")
    private String userPhone;

    @ApiModelProperty(value = "用户地址")
    private String userHomeAddress;

    @ApiModelProperty(value = "用户年龄")
    private Integer userAge;

    @ApiModelProperty(value = "爱好")
    private String userHobby;

    @ApiModelProperty(value = "生日")
    private String userBirthday;

    @ApiModelProperty(value = "备注")
    private String memo;

    @ApiModelProperty(value = "是否有效")
    private Boolean isUse;

    @ApiModelProperty(value = "入职时间")
    private LocalDateTime entryTime;

    @ApiModelProperty(value = "用户地址")
    private String recommenderId;

    @ApiModelProperty(value = "用户地址")
    private String recommenderName;

    @ApiModelProperty(value = "身份证号")
    private String userCord;

    @ApiModelProperty(value = "现住址")
    private String dormitoryAddress;

    @ApiModelProperty(value = "职位编号")
    private String positionId;

    @ApiModelProperty(value = "职位名称")
    private String positionName;

    @ApiModelProperty(value = "公司编号")
    private String companyCode;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "县")
    private String county;


}
