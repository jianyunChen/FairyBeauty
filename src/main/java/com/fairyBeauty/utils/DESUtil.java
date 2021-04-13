package com.fairyBeauty.utils;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * 加密解密帮助类
 *
 * @Author chenjianyun
 * @Date 2021/4/2 15:18
 * @Description 加密解密帮助类
 */
public class DESUtil {
    /** 安全密钥 */
    private static String keyData = "ABCDEFGHIJKLMNOPQRSTWXYZabcdefghijklmnopqrstwxyz0123456789-_.";

    /**
     * 功能：构造
     *
     * @author 陈箭云
     * @date 2014年07月03日
     */
    public DESUtil() {
    }

    /**
     * 功能：构造
     *
     * @author 陈箭云
     * @date 2014年07月03日
     * @param key
     *  key
     */
    public DESUtil(String key) {
        keyData = key;
    }

    /**
     * 功能：加密 (UTF-8)
     *
     * @author 陈箭云
     * @date 2014年07月03日
     * @param source
     *  源字符串
     * @param charSet
     *  编码
     * @return String
     * @throws UnsupportedEncodingException
     *  编码异常
     */
    public static String encrypt(String source) {
        return encrypt(source, "UTF-8");
    }

    /**
     *
     * 功能：解密 (UTF-8)
     *
     * @author 陈箭云
     * @date 2014年07月03日
     * @param encryptedData
     *  被加密后的字符串
     * @return String
     * @throws UnsupportedEncodingException
     *  编码异常
     */
    public static String decrypt(String encryptedData)
            throws UnsupportedEncodingException {
        return decrypt(encryptedData, "UTF-8");
    }

    /**
     * 功能：加密
     *
     * @author 陈箭云
     * @date 2014年07月03日
     * @param source
     *  源字符串
     * @param charSet
     *  编码
     * @return String
     * @throws UnsupportedEncodingException
     *  编码异常
     */
    public static String encrypt(String source, String charSet) {
        String encrypt = null;
        byte[] ret = new byte[0];
        try {
            ret = encrypt(source.getBytes(charSet));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        encrypt = new String(Base64.getEncoder().encodeToString(ret));
        return encrypt;
    }

    /**
     *
     * 功能：解密
     *
     * @author 陈箭云
     * @date 2014年07月03日
     * @param encryptedData
     *  被加密后的字符串
     * @param charSet
     *  编码
     * @return String
     * @throws UnsupportedEncodingException
     *  编码异常
     */
    public static String decrypt(String encryptedData, String charSet)
            throws UnsupportedEncodingException {
        String descryptedData = null;
        byte[] ret = descrypt(Base64.getDecoder().decode(encryptedData));
        descryptedData = new String(ret, charSet);
        return descryptedData;
    }

    /**
     * 加密数据 用生成的密钥加密原始数据
     *
     * @param primaryData
     *  原始数据
     * @return byte[]
     * @author 陈箭云
     * @date 2014年07月03日
     */
    private static byte[] encrypt(byte[] primaryData) {

        /** 取得安全密钥 */
        byte rawKeyData[] = getKey();

        /** DES算法要求有一个可信任的随机数源 */
        SecureRandom sr = new SecureRandom();

        /** 使用原始密钥数据创建DESKeySpec对象 */
        DESKeySpec dks = null;
        try {
            dks = new DESKeySpec(keyData.getBytes());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        /** 创建一个密钥工厂 */
        SecretKeyFactory keyFactory = null;
        try {
            keyFactory = SecretKeyFactory.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        /** 用密钥工厂把DESKeySpec转换成一个SecretKey对象 */
        SecretKey key = null;
        try {
            key = keyFactory.generateSecret(dks);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        /** Cipher对象实际完成加密操作 */
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        /** 用密钥初始化Cipher对象 */
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, sr);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        /** 正式执行加密操作 */
        byte encryptedData[] = null;
        try {
            encryptedData = cipher.doFinal(primaryData);
        }catch (Exception e) {
            e.printStackTrace();
        }

        /** 返回加密数据 */
        return encryptedData;
    }

    /**
     * 用密钥解密数据
     *
     * @param encryptedData
     *  加密后的数据
     * @return byte[]
     * @author 陈箭云
     * @date 2014年07月03日
     */
    private static byte[] descrypt(byte[] encryptedData) {

        /** DES算法要求有一个可信任的随机数源 */
        SecureRandom sr = new SecureRandom();

        /** 取得安全密钥 */
        byte rawKeyData[] = getKey();

        /** 使用原始密钥数据创建DESKeySpec对象 */
        DESKeySpec dks = null;
        try {
            dks = new DESKeySpec(keyData.getBytes());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        /** 创建一个密钥工厂 */
        SecretKeyFactory keyFactory = null;
        try {
            keyFactory = SecretKeyFactory.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        /** 用密钥工厂把DESKeySpec转换成一个SecretKey对象 */
        SecretKey key = null;
        try {
            key = keyFactory.generateSecret(dks);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        /** Cipher对象实际完成加密操作 */
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        /** 用密钥初始化Cipher对象 */
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, sr);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        /** 正式执行解密操作 */
        byte decryptedData[] = null;
        try {
            decryptedData = cipher.doFinal(encryptedData);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return decryptedData;
    }

    /**
     * 取得安全密钥 此方法作废,因为每次key生成都不一样导致解密加密用的密钥都不一样， 从而导致Given final block not
     * properly padded错误.
     *
     * @return byte数组
     * @author 陈箭云
     * @date 2014年07月03日
     */
    private static byte[] getKey() {
        /** 生成密钥byte数组 */
        byte rawKeyData[] = keyData.getBytes();

        return rawKeyData;
    }

}