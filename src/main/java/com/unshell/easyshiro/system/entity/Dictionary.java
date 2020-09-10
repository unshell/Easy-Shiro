package com.unshell.easyshiro.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.Data;

/**
 * 字典
 */
@Data
@TableName("system_dictionary")
public class Dictionary {
    /**
     * 配置编号
     */
    @TableId(value = "DictId", type = IdType.AUTO)
    private Integer dictId;
    /**
     * 组编号
     */
    @TableField("GroupId")
    private Integer groupId;
    /**
     * 配置名称
     */
    @TableField("DictName")
    private String dictName;
    /**
     * 唯一标识
     */
    @TableField("Sign")
    private String sign;
    /**
     * 键
     */
    @TableField("DictKey")
    private String dictKey;
    /**
     * 值
     */
    @TableField("DictValue")
    private String dictValue;
    /**
     * 是否为组
     */
    @TableField("IsGroup")
    private Boolean isGroup;
    /**
     * 排序
     */
    @TableField("Sort")
    private Integer sort;
    /**
     * 备注
     */
    @TableField("Remark")
    private String remark;

    public Dictionary() {
    }

    public Dictionary(String _dictName, String _dictKey) {
        this.dictName = _dictName;
        this.dictKey = _dictKey;
    }

    public Dictionary(String _dictName, String _dictKey, String _dictValue) {
        this.dictName = _dictName;
        this.dictKey = _dictKey;
        this.dictValue = _dictValue;
    }
}