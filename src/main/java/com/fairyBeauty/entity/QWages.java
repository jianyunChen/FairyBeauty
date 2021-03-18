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
 * 用户薪资待遇信息表
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("q_wages")
@ApiModel(value="QWages对象", description="用户薪资待遇信息表")
public class QWages extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编号")
    private String userId;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "底薪")
    private BigDecimal wages;

    @ApiModelProperty(value = "浮动薪资")
    private BigDecimal floatWages;

    @ApiModelProperty(value = "备注")
    private String memo;

    @ApiModelProperty(value = "职位编号")
    private String positionId;

    @ApiModelProperty(value = "职位名称")
    private String positionName;
}
