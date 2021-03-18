package com.fairyBeauty.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 客户评价表
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_customer_score")
@ApiModel(value="MCustomerScore对象", description="客户评价表")
public class MCustomerScore extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户编号")
    private String customerId;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "用户编号")
    private String userId;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "评分")
    private Integer score;

    @ApiModelProperty(value = "备注")
    private String memo;
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


}
