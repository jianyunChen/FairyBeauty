package com.fairyBeauty.entity.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 排序对象
 * @Author chenjianyun
 * @Date 2021/3/22 20:16
 * @Description 排序对象
 */
@Data
public class OrderByVo implements Serializable {
    /**
     * 排序字段名称
     */
    private String orderByName;
    /**
     * 排序方式 true正序 false倒序
     */
    private Boolean orderByAsc = true;
}
