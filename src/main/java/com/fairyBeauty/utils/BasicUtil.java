package com.fairyBeauty.utils;

import java.math.BigDecimal;

/**
 * 调用basic接口帮助类
 */
public class BasicUtil {
    /**
     * 字符串转 BigDecimal
     * @param val
     * @return
     */
    public static BigDecimal toBigDecimal(String val){
        return BigDecimal.valueOf(Double.valueOf(val));
    }

}
