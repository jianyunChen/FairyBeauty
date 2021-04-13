package com.fairyBeauty.entity.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询对象
 *
 * @Author chenjianyun
 * @Date 2021/3/22 20:19
 * @Description 查询对象
 */
@Data
public class QueryVo implements Serializable {
    /**
     * 字段名称
     */
    private String name;
    /**
     * 查询类型
     */
    private String type;
    /**
     * 字段值
     */
    private String value;
    /**
     * 级别
     */
    private Integer level = 0;
}
