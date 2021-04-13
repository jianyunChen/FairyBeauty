package com.fairyBeauty.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fairyBeauty.entity.BaseBean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 基类补充
 *
 * @Author chenjianyun
 * @Date 2021/4/7 16:31
 * @Description 基类补充
 */
public class BaceServiceImpl<M extends BaseBean,T extends BaseMapper<M>> extends ServiceImpl<T,M> {


    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    public boolean remove(Wrapper<M> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }


    @Override
    public M getById(Serializable id) {
        M entity = super.getById(id);
        if(entity.getIsDelete()){
            return null;
        }
        return entity;
    }

    @Override
    public List<M> listByIds(Collection<? extends Serializable> idList) {
        return checkIsDelete(super.listByIds(idList));
    }

    private List<M> checkIsDelete(List<M> list){
        return list.size() > 0 ? list.stream().filter(u->!u.getIsDelete()).collect(Collectors.toList()) : list;
    }
    private Wrapper<M> checkQueryWrapper(Wrapper<M> queryWrapper){
        return queryWrapper;
    }
    private Map<String, Object> checkQueryWrapper(Map<String, Object> columnMap){
        columnMap.put("is_delete", 0);
        return columnMap;
    }

    @Override
    public List<M> listByMap(Map<String, Object> columnMap) {
        checkQueryWrapper(columnMap);
        return checkIsDelete(super.listByMap(columnMap));
    }

    @Override
    public M getOne(Wrapper<M> queryWrapper) {
        checkQueryWrapper(queryWrapper);
        return super.getOne(queryWrapper);
    }

    @Override
    public int count() {
        return super.count(checkQueryWrapper(new QueryWrapper<>()));
    }

    @Override
    public int count(Wrapper<M> queryWrapper) {
        checkQueryWrapper(queryWrapper);
        return super.count(queryWrapper);
    }

    @Override
    public List<M> list(Wrapper<M> queryWrapper) {
        checkQueryWrapper(queryWrapper);
        return super.list(queryWrapper);
    }

    @Override
    public List<M> list() {
        Wrapper<M> queryWrapper = new QueryWrapper<>();
        checkQueryWrapper(queryWrapper);
        return super.list(queryWrapper);
    }

    @Override
    public <E extends IPage<M>> E page(E page, Wrapper<M> queryWrapper) {
        checkQueryWrapper(queryWrapper);
        return super.page(page,queryWrapper);
    }

    @Override
    public <E extends IPage<M>> E page(E page) {
        return super.page(page,checkQueryWrapper(new QueryWrapper<>()));
    }

    @Override
    public List<Map<String, Object>> listMaps(Wrapper<M> queryWrapper) {
        checkQueryWrapper(queryWrapper);
        return super.listMaps(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> listMaps() {
        return super.listMaps(checkQueryWrapper(new QueryWrapper<>()));
    }

    @Override
    public List<Object> listObjs() {
        return super.listObjs();
    }

    @Override
    public <V> List<V> listObjs(Function<? super Object, V> mapper) {
        return super.listObjs(mapper);
    }

    @Override
    public List<Object> listObjs(Wrapper<M> queryWrapper) {
        checkQueryWrapper(queryWrapper);
        return super.listObjs(queryWrapper);
    }

    @Override
    public <V> List<V> listObjs(Wrapper<M> queryWrapper, Function<? super Object, V> mapper) {
        checkQueryWrapper(queryWrapper);
        return super.listObjs(queryWrapper,mapper);
    }

    @Override
    public <E extends IPage<Map<String, Object>>> E pageMaps(E page, Wrapper<M> queryWrapper) {
        checkQueryWrapper(queryWrapper);
        return super.pageMaps(page,queryWrapper);
    }

    @Override
    public <E extends IPage<Map<String, Object>>> E pageMaps(E page) {
        return super.pageMaps(page,checkQueryWrapper(new QueryWrapper<>()));
    }

    @Override
    public QueryChainWrapper<M> query() {
        return super.query();
    }

    @Override
    public LambdaQueryChainWrapper<M> lambdaQuery() {
        return super.lambdaQuery();
    }

    @Override
    public UpdateChainWrapper<M> update() {
        return super.update();
    }

    @Override
    public LambdaUpdateChainWrapper<M> lambdaUpdate() {
        return super.lambdaUpdate();
    }

}
