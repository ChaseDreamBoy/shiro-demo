package com.xh.util;

import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * @author xiaohe
 * @version V1.0.0
 */
public class MyEncryptUtils {

    /**
     * 加密
     *
     * @param password 密码
     * @param salt     盐
     *
     * @return 密文
     */
    public static String encrypt(String password, String salt) {
        return new Sha256Hash(password, salt).toHex();
    }

}
