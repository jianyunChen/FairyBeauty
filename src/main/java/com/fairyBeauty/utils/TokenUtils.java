package com.fairyBeauty.utils;

import com.fairyBeauty.enums.ResponseEnum;
import com.fairyBeauty.enums.TokenExistEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * token帮助类
 *
 * @Author chenjianyun
 * @Date 2021/3/29 14:53
 * @Description token帮助类
 */
@Component
public class TokenUtils {
    @Resource
    private void setRedisUtils(RedisUtils redisUtils){
        TokenUtils.redisUtils = redisUtils;
    }
    private static RedisUtils redisUtils;
    /**
     * 根据用户名生成token
     * @param userId
     * @return
     */
    public static String getTokenByUserId(String userId) {
        UUID uuid = UUID.randomUUID();
        String token = BasicUtil.base64(userId + "_" + uuid.toString());
        System.out.println(System.currentTimeMillis());
        System.out.println(uuid.timestamp());
        return token;
    }

    /**
     * 验证用户token
     * @param userId 用户名
     * @param token token
     * @return
     */
    public static TokenExistEnum verify(String userId, String token) {
        token = BasicUtil.encodedBase64(token);
        if(!token.contains(userId + "_")){
            throw new WebResponseException(ResponseEnum.UN_KNOW_ERROR);
        }
        UUID uuid = UUID.fromString(token.replace( userId + "_",""));
       Long time = System.currentTimeMillis() - (uuid.timestamp() + RedisUtils.loginTime);
        if(time > 0 ){
            //超过缓存1半时间/12小时没有操作的返回超时
            if(System.currentTimeMillis() - (int)redisUtils.get(userId + "_") - (RedisUtils.loginTime / 2 < 12*3600? RedisUtils.loginTime / 2 : 12*3600) <0){
                throw new WebResponseException(ResponseEnum.TOKEN_ERROR_TIMEOUT);
            }else{
                return TokenExistEnum.REST;
            }
        }
        return TokenExistEnum.SUCCESS;
    }

    /**
     * 无感刷新用户token时间
     * @param userId 用户名
     * @param token token
     * @return
     */
    public static Boolean upTokenTime(String userId,String token) {
        userId = BasicUtil.encodedBase64(userId);
        if(!token.contains(userId)){
            throw new WebResponseException(ResponseEnum.UN_KNOW_ERROR);
        }
        UUID uuid = UUID.fromString(token.replace( userId + "$",""));
        if(uuid.timestamp() + RedisUtils.loginTime < System.currentTimeMillis()){
            return false;
        }
        return true;
    }
}
