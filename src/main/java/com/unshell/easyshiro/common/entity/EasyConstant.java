package com.unshell.easyshiro.common.entity;

/**
 * 常量
 */
public class EasyConstant {
    /**
     * 验证码 Session Key
     */
    public static final String CODE_PREFIX = "easy_captcha_";

    /**
     * 异步线程池名称
     */
    public static final String ASYNC_POOL = "easyAsyncThreadPool";

    /**
     * 允许下载的文件类型，根据需求添加（小写）
     */
    public static final String[] VALID_DOWNLOAD_FILE_TYPE = {"xlsx", "zip"};

    /**
     * 允许上传的文件类型，根据需求添加（小写）
     */
    public static final String[] VALID_UPLOAD_FILE_TYPE = {"img", "zip"};
}