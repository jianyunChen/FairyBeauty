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
 * 角色表
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_role")
@ApiModel(value="MRole对象", description="角色表")
public class MRole extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色编号")
    private String roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色类型")
    private String roleType;

    @ApiModelProperty(value = "上层角色编号")
    private String roleParentId;

    @ApiModelProperty(value = "是否有效")
    private String roleIsUsed;

    @ApiModelProperty(value = "备注")
    private String memo;
}
