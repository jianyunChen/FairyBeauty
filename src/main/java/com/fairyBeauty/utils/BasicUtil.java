package com.fairyBeauty.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Base64;

/**
 * 调用basic接口帮助类
 */
public class BasicUtil {
    private static BigDecimal bigDecimal;
    public BasicUtil(){

    }
    public BasicUtil(BigDecimal val){
        bigDecimal = val;
    }
    /**
     * 字符串转 BigDecimal
     * @param val
     * @return
     */
    public static BigDecimal toBigDecimal(String val){
        return BigDecimal.valueOf(Double.valueOf(val));
    }

    /**
     * 保留N位小数
     * @param num 保留位数
     * @return
     */
    public static BigDecimal bigDecimalSetScaleByNum(Integer num){
        return bigDecimal.setScale(num, RoundingMode.HALF_UP);
    }

    /**
     * 字保留2位小数
     * @return
     */
    public static BigDecimal bigDecimalSetScale(){
        return bigDecimalSetScaleByNum(2);
    }

    /**
     * Base64加密
     * @param str
     * @return
     */
    public static String base64(String str) {
        byte[] bytes = str.getBytes();

        //Base64 加密
        String encoded = Base64.getEncoder().encodeToString(bytes);
        return encoded;
    }

    /**
     * Base64 解密
     * @param str
     * @return
     */
    public static String encodedBase64(String str) {
        byte[] decoded = Base64.getDecoder().decode(str);
        String decodeStr = new String(decoded);
        return decodeStr;
    }



    public static void main(String[] args) {
        String s =  base64("admin");
        System.out.println(s);
        String s1 =  encodedBase64(s);
        System.out.println(s1);
    }

}
