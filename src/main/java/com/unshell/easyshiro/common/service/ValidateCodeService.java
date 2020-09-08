package com.unshell.easyshiro.common.service;

import com.unshell.easyshiro.common.entity.EasyConstant;
import com.unshell.easyshiro.common.entity.ImageType;
import com.unshell.easyshiro.common.properties.EasyProperties;
import com.unshell.easyshiro.common.properties.ValidateCodeProperties;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 验证码服务
 */
@Service
@RequiredArgsConstructor
public class ValidateCodeService {
    private final EasyProperties properties;
    private final RedisService redisService;

    public void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String key = session.getId();
        ValidateCodeProperties validate = properties.getValidate();
        setHeader(response, validate.getType());

        Captcha captcha;
        if (StringUtils.equalsIgnoreCase(validate.getType(), ImageType.GIF)) {
            captcha = new GifCaptcha(validate.getWidth(), validate.getHeight(), validate.getLength());
        } else {
            captcha = new SpecCaptcha(validate.getWidth(), validate.getHeight(), validate.getLength());
        }
        captcha.setCharType(validate.getCharType());

        redisService.set(EasyConstant.CODE_PREFIX + key, StringUtils.lowerCase(captcha.text()), validate.getTime());

        captcha.out(response.getOutputStream());
    }

    public void check(String key, String value) throws RuntimeException {
        Object codeInRedis = redisService.get(EasyConstant.CODE_PREFIX + key);
        if (StringUtils.isBlank(value)) {
            throw new RuntimeException("请输入验证码");
        }
        if (codeInRedis == null) {
            throw new RuntimeException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(value, String.valueOf(codeInRedis))) {
            throw new RuntimeException("验证码不正确");
        }
    }

    private void setHeader(HttpServletResponse response, String type) {
        if (StringUtils.equalsIgnoreCase(type, ImageType.GIF)) {
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }
}