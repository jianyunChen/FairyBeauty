package com.fairyBeauty.enums;

import com.fairyBeauty.utils.RedisUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * web请求返回状态码
 */
public enum RedisKeysEnum {
    /** 用户最后操作时间 */
    USER_LASTER_UPDATETIME("USER_LASTER_UPDATETIME","用户信息"),
    /** 用户信息 */
    USER_INFO("USER_INFO","用户信息"),
    /** 用户token信息 */
    USER_TOKEN("USER_TOKEN","用户token信息"),
    ;
    /**
     * 初始化
     * @param name
     * @param message
     */
    RedisKeysEnum(String name,String message){
        setKey(name);
        setMessage(message);
        setSignOutDel(true);
        setTime(RedisUtils.loginTime);
    }
    /**
     * 初始化
     * @param name
     * @param message
     */
    RedisKeysEnum(String name,String message, Boolean signOutDel,Integer time){
        setKey(name);
        setMessage(message);
        setSignOutDel(signOutDel);
        setTime(time);
    }

    /**
     * 主键
     */
    private String key;
    /**
     * 说明
     */
    private String message;

    /**
     * 退出是否删除缓存 默认删除
     */
    private Boolean signOutDel = true;

    /**
     * 默认缓存时间
     */
    private Integer time = 0;


    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSignOutDel() {
        return signOutDel;
    }

    public void setSignOutDel(Boolean signOutDel) {
        this.signOutDel = signOutDel;
    }


    /**
     * 获取删除/不删除的指定Redis 主键
     * @param signOutDel 退出是否删除 默认是
     * @return
     */
    public static String[] getKeys(Boolean signOutDel){
        List<String> keys = new ArrayList<>();
        for (RedisKeysEnum redisKeys : RedisKeysEnum.values()) {
            if(signOutDel == null || signOutDel.equals(redisKeys.getSignOutDel())){
                keys.add(redisKeys.getKey());
            }
        }
        String[] result = keys.toArray(new String[keys.size()]);
        return result;
    }
    /**
     * 获取删除/不删除的指定Redis 主键
     * @param key
     * @return
     */
    public static RedisKeysEnum getKeyByName(String key){
        for (RedisKeysEnum redisKeys : RedisKeysEnum.values()) {
            if(key.equals(redisKeys.getKey())){
                return redisKeys;
            }
        }
        return null;
    }
}
