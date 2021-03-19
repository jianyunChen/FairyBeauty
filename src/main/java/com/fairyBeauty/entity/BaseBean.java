package com.fairyBeauty.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime = LocalDateTime.now();

    @ApiModelProperty(value = "更新人")
    private String updateUserId = "";

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime = LocalDateTime.now();

    @ApiModelProperty(value = "创建人")
    private String createUserId = "";

    @ApiModelProperty(value = "是否删除")
    private Boolean isDelete = false;

    @ApiModelProperty(value = "删除时间")
    private LocalDateTime deleteTime;

    @ApiModelProperty(value = "删除人")
    private String deleteUserId;
}
