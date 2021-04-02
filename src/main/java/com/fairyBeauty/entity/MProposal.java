package com.fairyBeauty.entity;

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
 * 建议收集表
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_proposal")
@ApiModel(value="MProposal对象", description="建议收集表")
public class MProposal extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户/员工编号")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;


    @ApiModelProperty(value = "类型 0客户 1员工")
    private Integer type;

    @ApiModelProperty(value = "备注")
    private String memo;
}
