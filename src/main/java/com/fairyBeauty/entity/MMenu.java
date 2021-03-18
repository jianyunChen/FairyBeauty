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
 * 菜单表
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_menu")
@ApiModel(value="MMenu对象", description="菜单表")
public class MMenu extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单编号")
    private String menuId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单名称")
    private String menuUrl;

    @ApiModelProperty(value = "菜单名称")
    private String menuType;

    @ApiModelProperty(value = "上层菜单编号")
    private String menuParentId;

    @ApiModelProperty(value = "是否显示")
    private String menuIsShow;

    @ApiModelProperty(value = "是否有效")
    private String menuIsUsed;

    @ApiModelProperty(value = "备注")
    private String memo;
}
