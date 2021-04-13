package com.fairyBeauty.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author chenjianyun
 * @Date 2021/3/18 11:19
 * @Description 基本信息
 */
@Data
public class BaseBean implements Serializable {
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime = LocalDateTime.now();
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateUserId = "";
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime = LocalDateTime.now();
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createUserId = "";
    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private Boolean isDelete = false;
    /**
     * 删除时间
     */
    @ApiModelProperty(value = "删除时间")
    private LocalDateTime deleteTime;
    /**
     * 删除人
     */
    @ApiModelProperty(value = "删除人")
    private String deleteUserId;
}
