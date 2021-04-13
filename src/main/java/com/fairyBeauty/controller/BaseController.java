package com.fairyBeauty.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fairyBeauty.entity.BaseBean;
import com.fairyBeauty.entity.base.OrderByVo;
import com.fairyBeauty.entity.base.PageVo;
import com.fairyBeauty.entity.base.QueryVo;
import com.fairyBeauty.entity.base.WebResponseVo;
import com.fairyBeauty.enums.ResponseEnum;
import com.fairyBeauty.utils.WebResponseException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @Author chenjianyun
 * @Date 2021/3/22 18:35
 * @Description
 */
public class BaseController<T extends BaseBean, M extends IService> implements Serializable {
    protected Class<?> entityClass = this.currentModelClass();

    public BaseController() {
    }

    /**
     * controller对应的services对象
     */
    @Autowired
    protected M baseServiceImpl;

    protected Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), 1);
    }

    @ApiOperation(value = "新增", notes = "add")
    @ApiImplicitParam(value = "保存对象", name = "entity", required = true)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public WebResponseVo add(T entity) {
        WebResponseVo response;
        try {
            //检测新增数据正确性
            checkInsertData(entity);
            //新增
            if (baseServiceImpl.save(entity)) {
                response = new WebResponseVo("新增成功！");
            } else {
                response = new WebResponseVo("新增失败！");
            }
        } catch (WebResponseException e) {
            //返回错误信息
            response = new WebResponseVo(e);
        }catch (Exception ex){
            //返回错误信息
            response = new WebResponseVo(ResponseEnum.UN_KNOW_ERROR,ex.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "修改", notes = "update")
    @ApiImplicitParam(value = "保存对象", name = "entity", required = true)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public WebResponseVo update(T entity) {
        WebResponseVo response;
        try {
            //获取旧数据
            T oldEntyty = (T) baseServiceImpl.getById(entity.getId());
            if (baseServiceImpl.getById(entity.getId()) == null) {
                throw new WebResponseException(ResponseEnum.NOT_FIND);
            }
            //检测修改数据正确性
            checkUpdateData(entity);
            /**
             * 更新时间
             */
            entity.setUpdateTime(LocalDateTime.now());
            //返回数据
            //修改
            if (baseServiceImpl.updateById(entity)) {
                response = new WebResponseVo("修改成功！");
            } else {
                response = new WebResponseVo("修改失败！");
            }
        } catch (WebResponseException e) {
            //返回错误信息
            response = new WebResponseVo(e);
        }catch (Exception ex){
            //返回错误信息
            response = new WebResponseVo(ResponseEnum.UN_KNOW_ERROR,ex.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "逻辑删除", notes = "deleteById")
    @ApiImplicitParam(value = "保存对象", name = "entity", required = true)
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public WebResponseVo deleteById(T entity) {
        WebResponseVo response;
        try {
            //返回数据
            if (entity.getId() == null || entity.getId() < 1) {
                throw new WebResponseException(ResponseEnum.PRIMARY_KEY_NULL);
            }
            T oldEntity = (T) baseServiceImpl.getById(entity.getId());
            if (oldEntity == null) {
                throw new WebResponseException(ResponseEnum.NOT_FIND);
            }
            oldEntity.setDeleteUserId(entity.getDeleteUserId());
            oldEntity.setDeleteTime(LocalDateTime.now());
            oldEntity.setIsDelete(true);
            //删除
            if (baseServiceImpl.updateById(entity)) {
                response = new WebResponseVo("删除成功！");
            } else {
                response = new WebResponseVo("删除失败！");
            }
        } catch (WebResponseException e) {
            //返回错误信息
            response = new WebResponseVo(e);
        }catch (Exception ex){
            //返回错误信息
            response = new WebResponseVo(ResponseEnum.UN_KNOW_ERROR,ex.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "获取单条详情", notes = "findById")
    @ApiImplicitParam(value = "保存对象", name = "entity", required = true)
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public WebResponseVo findById(Long id) {
        WebResponseVo response;
        try {
            //返回数据
            if (id == null || id < 1) {
                throw new WebResponseException(ResponseEnum.PRIMARY_KEY_NULL);
            }
            T entity = (T) baseServiceImpl.getById(id);
            //未找到指定数据
            if (entity == null) {
                throw new WebResponseException(ResponseEnum.NOT_FIND);
            }
            response = new WebResponseVo();
        } catch (WebResponseException e) {
            //返回错误信息
            response = new WebResponseVo(e);
        }catch (Exception ex){
            //返回错误信息
            response = new WebResponseVo(ResponseEnum.UN_KNOW_ERROR,ex.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "分页查询", notes = "selectPageList")
    @ApiImplicitParam(value = "分页查询对象", name = "list", required = true)
    @RequestMapping(value = "/selectPageList", method = RequestMethod.POST)
    public WebResponseVo selectPageList(PageVo<T> list) {
        WebResponseVo response;
        try {
            //初始化查询条件
            QueryWrapper wrapper = getQueryWrapper(list);
            //返回数据
            Object data = null;
            //设置分页信息
            IPage<T> iPage = new Page<T>();
            iPage.setSize(list.getPageSize() > 0 ? list.getPageSize() : 10);
            iPage.setCurrent(list.getPageNumber()> 0 ? list.getPageNumber() : 1);

            //排序
            if (CollectionUtils.isEmpty(list.getOrderByList())) {
                wrapper.orderByDesc("update_time");
            } else {
                //正序
                if (list.getOrderByList().stream().anyMatch(u -> u.getOrderByAsc())) {
                    wrapper.orderByDesc(list.getOrderByList().stream().filter(u -> u.getOrderByAsc()).map(OrderByVo::getOrderByName).collect(Collectors.toList()));
                }
                //倒叙
                if (list.getOrderByList().stream().anyMatch(u -> !u.getOrderByAsc())) {
                    wrapper.orderByDesc(list.getOrderByList().stream().filter(u -> !u.getOrderByAsc()).map(OrderByVo::getOrderByName).collect(Collectors.toList()));
                }
                wrapper.orderByAsc("update_time");
            }
            //获取查询列表
            iPage = baseServiceImpl.pageMaps(iPage, wrapper);
            int totalNum = baseServiceImpl.count(wrapper);
            //设置返回数据
            list.setList(iPage.getRecords());
            list.setTotalCount((long)totalNum);
            list.setTotalPageCount((int) Math.ceil((list.getTotalCount() / list.getPageNumber())));
            baseServiceImpl.listMaps(wrapper);
            response = new WebResponseVo(list);
        } catch (WebResponseException e) {
            //返回错误信息
            response = new WebResponseVo(e);
        }catch (Exception ex){
            //返回错误信息
            response = new WebResponseVo(ResponseEnum.UN_KNOW_ERROR,ex.getMessage());
        }
        return response;
    }

    /**
     * 查询条件方法 可覆盖，默认无查询条件
     *
     * @param list
     * @return
     */
    public QueryWrapper getQueryWrapper(PageVo<T> list) {
        QueryWrapper queryWrapper = new QueryWrapper();
        //默认查询未删除的数据
        queryWrapper.eq("is_delete", 0);
        if (CollectionUtils.isNotEmpty(list.getQueryList())) {
            getQueryByPageVo(list.getQueryList(), queryWrapper);
        }
        return queryWrapper;
    }

    /**
     * 自动匹配查询条件（仅简单类型，复杂类型需自行添加）
     * @param queryVoList 查询条件列表
     * @param queryWrapper 查询条件集合对象
     */
    public void getQueryByPageVo(List<QueryVo> queryVoList, QueryWrapper queryWrapper) {
        AtomicReference<Integer> level = new AtomicReference<>(0);
        for (QueryVo queryVo : queryVoList) {
            level.set(queryVo.getLevel());
            if (StringUtils.isEmpty(queryVo.getName())) {
                continue;
            }
            //查询类型
            switch (queryVo.getType()) {
                case "=":
                    queryWrapper.eq(queryVo.getName(), queryVo.getValue());
                    break;
                case "!=":
                    queryWrapper.ne(queryVo.getName(), queryVo.getValue());
                    break;
                case ">":
                    queryWrapper.gt(queryVo.getName(), queryVo.getValue());
                    break;
                case "<":
                    queryWrapper.lt(queryVo.getName(), queryVo.getValue());
                    break;
                case ">=":
                    queryWrapper.ge(queryVo.getName(), queryVo.getValue());
                    break;
                case "<=":
                    queryWrapper.le(queryVo.getName(), queryVo.getValue());
                    break;
                case "isNull":
                    queryWrapper.isNull(queryVo.getName());
                    break;
                case "isNotNull":
                    queryWrapper.isNotNull(queryVo.getName());
                    break;
                case "likeLeft":
                    queryWrapper.likeLeft(queryVo.getName(), queryVo.getValue());
                    break;
                case "likeRight":
                    queryWrapper.likeRight(queryVo.getName(), queryVo.getValue());
                    break;
                case "like":
                    queryWrapper.like(queryVo.getName(), queryVo.getValue());
                    break;
                case "notLike":
                    queryWrapper.notLike(queryVo.getName(), queryVo.getValue());
                    break;
                case "in":
                    queryWrapper.in(queryVo.getName(), Arrays.asList(queryVo.getValue().split(",")));
                    break;
                case "notIn":
                    queryWrapper.notIn(queryVo.getName(), Arrays.asList(queryVo.getValue().split(",")));
                    break;
                case "groupBy":
                    queryWrapper.groupBy(Arrays.asList(queryVo.getName().split(",")));
                    queryWrapper.select(queryVo.getName().split(","));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 查询条件变为map对象
     * @param queryVoList 查询条件列表
     * @return
     */
    public Map<String,String> getQueryByPageVoToMap(List<QueryVo> queryVoList){
        Map<String,String> queryMap = new HashMap<>();
        queryMap = queryVoList.stream().collect(Collectors.toMap(QueryVo::getName, QueryVo::getValue));
        return queryMap;
    }
    /**
     * 检测新增数据正确性
     *
     * @param entity
     */
    public void checkInsertData(T entity) {
    }

    /**
     * 检测修改数据正确性
     *
     * @param entity
     */
    public void checkUpdateData(T entity) {
    }
}
