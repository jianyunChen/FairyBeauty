package com.fairyBeauty.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 日流水记录表
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("q_payroll_day_details")
@ApiModel(value="QPayrollDayDetails对象", description="日流水记录表")
public class QPayrollDayDetails extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编号")
    private String userId;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "业绩")
    private BigDecimal achievement;

    @ApiModelProperty(value = "手工费")
    private BigDecimal manualCost;

    @ApiModelProperty(value = "服务人数")
    private Integer serviceCount;

    @ApiModelProperty(value = "服务老客")
    private Integer serviceCountOld;

    @ApiModelProperty(value = "服务新客")
    private Integer serviceCountNew;

    @ApiModelProperty(value = "老客销售人数")
    private Integer sellCountOld;

    @ApiModelProperty(value = "新客销售人数")
    private Integer sellCountNew;

    @ApiModelProperty(value = "老客销售金额")
    private BigDecimal sellAmountOld;

    @ApiModelProperty(value = "新客销售金额")
    private BigDecimal sellAmountNew;

    @ApiModelProperty(value = "备注")
    private String memo;

    @ApiModelProperty(value = "日期")
    private LocalDate qDate;


}
