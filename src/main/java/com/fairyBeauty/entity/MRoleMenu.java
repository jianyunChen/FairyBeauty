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
 * 角色菜单表
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_role_menu")
@ApiModel(value="MRoleMenu对象", description="角色菜单表")
public class MRoleMenu extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色编号")
    private String roleId;

    @ApiModelProperty(value = "菜单编号 多选")
    private String menuIds;

    @ApiModelProperty(value = "备注")
    private String memo;
}
