package com.fairyBeauty.entity.base;

import com.fairyBeauty.entity.BaseBean;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询
 * @Author chenjianyun
 * @Date 2021/3/22 18:07
 * @Description 分页查询
 */
@Data
public class PageVo<T extends BaseBean> implements Serializable {
    /**
     * 分页大小
     */
    private Integer pageSize = 10;
    /**
     * 当前页
     */
    private Integer pageNumber = 1;
    /**
     * 总条数
     */
    private Long totalCount = 0L;
    /**
     * 总页数
     */
    private Integer totalPageCount = 0;
    /**
     * 返回列表对象
     */
    private List<T> list = null;
    /**
     * 查询条件
     */
    private List<QueryVo> queryList;
    /**
     * 排序字段:字段+排序方式 （0正序 1倒叙）
     */
    private List<OrderByVo> orderByList ;
}

