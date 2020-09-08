package com.unshell.easyshiro.monitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.common.utils.EasyUtil;
import com.unshell.easyshiro.monitor.entity.Log;
import com.unshell.easyshiro.monitor.mapper.LogMapper;
import com.unshell.easyshiro.monitor.service.ILogService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

@Service
@RequiredArgsConstructor
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {
    private final ObjectMapper objectMapper;

    /**
     * 查询监控日志分页信息
     *
     * @param request
     * @return
     */
    @Override
    public IPage<Log> queryLogPage(QueryRequest request, Log log) {
        LambdaQueryWrapper<Log> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(log.getOperator())) {
            wrapper.eq(Log::getOperator, log.getOperator());
        }
        if (StringUtils.isNotBlank(log.getStartDate()) && StringUtils.isNotBlank(log.getEndDate())) {
            wrapper.ge(Log::getCreateTime, log.getStartDate()).le(Log::getCreateTime, log.getEndDate());
        }
        wrapper.orderByDesc(Log::getCreateTime);
        Page<Log> page = new Page<>(request.getPage(), request.getLimit());
        return this.page(page, wrapper);
    }

    /**
     * 查询当天日志信息
     *
     * @return
     */
    @Override
    public List<Log> queryLogListToday(Integer limit) {
        return this.baseMapper.queryLogListToday(limit);
    }

    /**
     * 保存监控日志(异步)
     *
     * @param point     切点
     * @param method    方法
     * @param ip        IP
     * @param operation 操作内容
     * @param start     开始时间
     */
    @Override
    public void saveLog(ProceedingJoinPoint point, Method method, String ip, String operator, String operation, boolean isPrivacy, long start) {
        Log log = new Log();
        // 设置IP
        log.setIpAddress(ip);
        if (StringUtils.isBlank(operator)) {
            operator = EasyUtil.getCurrentLoginName();
        }
        // 设置操作者
        log.setOperator(operator);
        // 设置耗时
        log.setTime(System.currentTimeMillis() - start);
        // 设置操作内容
        log.setOperation(operation);
        // 设置操作的方法
        String className = point.getTarget().getClass().getName();
        String methodName = method.getName();
        log.setMethod(className + StringPool.DOT + methodName);

        // 非隐私数据情况下设置方法参数
        if (!isPrivacy) {
            // 设置方法参数值
            Object[] args = point.getArgs();
            // 设置方法参数名称
            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
            String[] paramNames = u.getParameterNames(method);
            if (args != null && paramNames != null) {
                StringBuilder params = new StringBuilder();
                params = handleParams(params, args, Arrays.asList(paramNames));
                log.setParams(params.toString());
            }
        } else {
            log.setParams("参数内容涉及隐私部分");
        }

        log.setCreateTime(new Date());
        // 保存系统日志
        this.save(log);
    }

    @SuppressWarnings("all")
    private StringBuilder handleParams(StringBuilder params, Object[] args, List paramNames) {
        try {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Map) {
                    Set set = ((Map) args[i]).keySet();
                    List<Object> list = new ArrayList<>();
                    List<Object> paramList = new ArrayList<>();
                    for (Object key : set) {
                        list.add(((Map) args[i]).get(key));
                        paramList.add(key);
                    }
                    return handleParams(params, list.toArray(), paramList);
                } else {
                    if (args[i] instanceof Serializable) {
                        Class<?> aClass = args[i].getClass();
                        try {
                            aClass.getDeclaredMethod("toString", new Class[]{null});
                            // 如果不抛出 NoSuchMethodException 异常则存在 toString 方法 ，安全的 writeValueAsString ，否则 走 Object的 toString方法
                            params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i]));
                        } catch (NoSuchMethodException e) {
                            params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i].toString()));
                        }
                    } else if (args[i] instanceof MultipartFile) {
                        MultipartFile file = (MultipartFile) args[i];
                        params.append(" ").append(paramNames.get(i)).append(": ").append(file.getName());
                    } else {
                        params.append(" ").append(paramNames.get(i)).append(": ").append(args[i]);
                    }
                }
            }
        } catch (Exception ignore) {
            params.append("参数解析失败");
        }
        return params;
    }
}