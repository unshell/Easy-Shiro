package com.unshell.easyshiro.common.utils;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.UUID;

/**
 * Md5加密
 */
public class Md5Util {
    private static final String ALGORITHM_NAME = "md5";

    private static final int HASH_ITERATIONS = 5;

    public static String encrypt(String signature, String password) {
        String source = StringUtils.lowerCase(signature);
        password = StringUtils.lowerCase(password);
        return new SimpleHash(ALGORITHM_NAME, password, ByteSource.Util.bytes(source), HASH_ITERATIONS).toHex();
    }

    public static String signature() {
        return StringUtils.replace(UUID.randomUUID().toString(), StringPool.DASH, StringPool.EMPTY);
    }
}