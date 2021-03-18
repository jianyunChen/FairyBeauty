package com.fairyBeauty.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 区域信息表
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_area")
@ApiModel(value="SysArea对象", description="区域信息表")
public class SysArea implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "地区编号")
    private String areaId;

    @ApiModelProperty(value = "地区名称")
    private String areaName;

    @ApiModelProperty(value = "上层地区编号")
    private String parentAreaId;

    @ApiModelProperty(value = "上层地区名称")
    private String parentAreaName;


}
