package com.fairyBeauty.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 客户信息表
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_customer")
@ApiModel(value="MCustomer对象", description="客户信息表")
public class MCustomer extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户编号")
    private String customerId;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "客户电话")
    private String customerPhone;

    @ApiModelProperty(value = "客户地址")
    private String customerAddress;

    @ApiModelProperty(value = "客户地址")
    private String customerCompanyname;

    @ApiModelProperty(value = "余额")
    private BigDecimal customerBalance;

    @ApiModelProperty(value = "总充值金额")
    private BigDecimal customerRechargeTotal;

    @ApiModelProperty(value = "上次充值金额")
    private BigDecimal customerRechargeLast;

    @ApiModelProperty(value = "客户年龄")
    private Integer customerAge;

    @ApiModelProperty(value = "爱好")
    private String customerHobby;

    @ApiModelProperty(value = "客户生日")
    private String customerBirthday;

    @ApiModelProperty(value = "备注")
    private String memo;
}
