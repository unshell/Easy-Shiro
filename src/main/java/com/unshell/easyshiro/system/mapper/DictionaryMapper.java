package com.unshell.easyshiro.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.unshell.easyshiro.system.entity.Dictionary;
import org.apache.ibatis.annotations.Select;

public interface DictionaryMapper extends BaseMapper<Dictionary> {
    @Select("SELECT COUNT(*) FROM system_dictionary WHERE Sign = #{dictKey}")
    boolean hasDuplicateKey(String dictKey);
}