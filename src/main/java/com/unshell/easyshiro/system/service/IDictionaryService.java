package com.unshell.easyshiro.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.system.entity.Dictionary;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IDictionaryService extends IService<Dictionary> {
    /**
     * 查询字典分页信息
     *
     * @param request
     * @param dictionary
     * @return
     */
    IPage<Dictionary> queryDictionaryPage(QueryRequest request, Dictionary dictionary);

    /**
     * 查询字典组信息
     *
     * @return
     */
    List<Dictionary> queryDictionaryGroupList(Dictionary dictionary);

    /**
     * 根据字典键查询字典值
     *
     * @param key 字典键
     * @return
     */
    Dictionary queryDictionary(String key, Boolean isGroup);

    /**
     * 根据字典组键查询所属字典项集合
     *
     * @param groupKey
     * @return
     */
    <K, V> Map<K, V> queryDictionaryMap(String groupKey);

    /**
     * 检查重复Key
     *
     * @param dictKey
     * @return
     */
    boolean hasDuplicateKey(String dictKey);

    /**
     * 新增字典
     *
     * @param dictionary
     */
    void insertDictionary(Dictionary dictionary);

    /**
     * 更新字典
     *
     * @param dictionary
     */
    void updateDictionary(Dictionary dictionary);

    /**
     * 删除字典
     *
     * @param dictId 主键
     */
    void deleteDictionary(Integer dictId);

    /**
     * 删除字典
     *
     * @param dictId 主键集
     */
    void deleteDictionary(Set<Integer> dictId);
}