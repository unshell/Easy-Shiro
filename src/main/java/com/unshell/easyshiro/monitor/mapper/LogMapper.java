package com.unshell.easyshiro.monitor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.unshell.easyshiro.monitor.entity.Log;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LogMapper extends BaseMapper<Log> {
    @Select("SELECT * FROM monitor_log WHERE TO_DAYS(CreateTime) = TO_DAYS(NOW()) ORDER BY CreateTime DESC LIMIT #{limit}")
    List<Log> queryLogListToday(Integer limit);
}