package com.fairyBeauty.aop;

import org.hibernate.bytecode.enhance.spi.interceptor.AbstractInterceptor;

import java.io.Serializable;

/**
 * @Author chenjianyun
 * @Date 2021/3/18 18:39
 * @Description 前端拦截器
 */
public class ActionAop extends AbstractInterceptor {

    public ActionAop(String entityName) {
        super(entityName);
    }

    @Override
    protected Object handleRead(Object o, String s, Object o1) {
        return null;
    }

    @Override
    protected Object handleWrite(Object o, String s, Object o1, Object o2) {
        return null;
    }
}
