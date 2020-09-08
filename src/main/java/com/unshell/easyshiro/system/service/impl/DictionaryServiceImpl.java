package com.unshell.easyshiro.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.system.entity.Dictionary;
import com.unshell.easyshiro.system.mapper.DictionaryMapper;
import com.unshell.easyshiro.system.service.IDictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {
    /**
     * 查询字典项分页信息
     *
     * @param request
     * @param dictionary
     * @return
     */
    @Override
    public IPage<Dictionary> queryDictionaryPage(QueryRequest request, Dictionary dictionary) {
        LambdaQueryWrapper<Dictionary> wrapper = new LambdaQueryWrapper<>();
        if (dictionary.getGroupId() != null) {
            wrapper.eq(Dictionary::getGroupId, dictionary.getGroupId());
        }
        if (StringUtils.isNotBlank(dictionary.getDictName())) {
            wrapper.like(Dictionary::getDictName, dictionary.getDictName());
        }
        if (StringUtils.isNotBlank(dictionary.getDictKey())) {
            wrapper.like(Dictionary::getDictKey, dictionary.getDictKey());
        }
        wrapper.orderByAsc(Dictionary::getSort);
        Page<Dictionary> page = new Page<>(request.getPage(), request.getLimit());
        return this.page(page, wrapper);
    }

    /**
     * 查询字典组信息
     *
     * @return
     */
    @Override
    public List<Dictionary> queryDictionaryGroupList(Dictionary dictionary) {
        LambdaQueryWrapper<Dictionary> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(dictionary.getDictName())) {
            wrapper.like(Dictionary::getDictName, dictionary.getDictName());
        }
        wrapper.eq(Dictionary::getIsGroup, true);
        return this.baseMapper.selectList(wrapper);
    }

    /**
     * 根据字典键查询字典值
     *
     * @param sign 字典键
     * @return
     */
    @Override
    public Dictionary queryDictionary(String sign, Boolean isGroup) {
        LambdaQueryWrapper<Dictionary> wrapper = new LambdaQueryWrapper<>();
        if (isGroup != null) {
            wrapper.eq(Dictionary::getIsGroup, isGroup);
        }
        wrapper.eq(Dictionary::getDictKey, sign);
        wrapper.last("LIMIT 1");
        return this.baseMapper.selectOne(wrapper);
    }

    /**
     * 根据字典组键查询所属字典项集合
     *
     * @param groupKey 字典键
     * @return
     */
    @Override
    public Map queryDictionaryMap(String groupKey) {
        Dictionary dictionary = this.queryDictionary(groupKey, true);
        Assert.notNull(dictionary, "字典组{" + groupKey + "}不存在");
        QueryWrapper<Dictionary> wrapper = new QueryWrapper<>();
        wrapper.select("DictKey", "DictValue");
        wrapper.lambda().eq(Dictionary::getIsGroup, false);
        wrapper.lambda().eq(Dictionary::getGroupId, dictionary.getDictId());
        return this.baseMapper.selectList(wrapper)
                .stream()
                .collect(Collectors.toMap(Dictionary::getDictKey, Dictionary::getDictValue));
    }

    /**
     * 检查重复Key
     *
     * @param dictKey
     * @return
     */
    @Override
    public boolean hasDuplicateKey(String dictKey) {
        return this.baseMapper.hasDuplicateKey(dictKey);
    }

    /**
     * 新增字典
     *
     * @param dictionary
     */
    @Override
    @Transactional
    public void insertDictionary(Dictionary dictionary) {
        if (dictionary.getGroupId() == null) {
            Assert.isTrue(!hasDuplicateKey(dictionary.getDictKey()), "存在重复字典组");
            dictionary.setIsGroup(true);
        } else {
            Dictionary group = this.baseMapper.selectById(dictionary.getGroupId());
            dictionary.setSign(group.getDictKey() + StringPool.AMPERSAND + dictionary.getDictKey());
            Assert.isTrue(!hasDuplicateKey(dictionary.getSign()), "该字典组中存在相同字典键");
            dictionary.setIsGroup(false);
        }
        this.save(dictionary);
    }

    /**
     * 更新字典
     *
     * @param dictionary
     */
    @Override
    @Transactional
    public void updateDictionary(Dictionary dictionary) {
        if (dictionary.getDictId() != null) {
            dictionary.setDictKey(null);
            this.baseMapper.updateById(dictionary);
        } else {
            LambdaQueryWrapper<Dictionary> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Dictionary::getDictKey, dictionary.getDictKey());
            this.baseMapper.update(dictionary, wrapper);
        }
    }

    /**
     * 更新字典集合
     *
     * @param group
     * @param map
     */
    @Override
    public void updateDictionaryMap(Dictionary group, Map<String, String> map) {
        Map<String, Object> dictMap = this.queryDictionaryMap(group.getDictKey());

        map.forEach((key, value) -> {
            if (dictMap.containsKey(key)) {
                this.updateDictionary(new Dictionary(null, key, value));
            } else {
                Dictionary dictionary = new Dictionary(null, key, value);
                dictionary.setGroupId(group.getDictId());
                dictionary.setSign(group.getDictKey() + StringPool.AMPERSAND + dictionary.getDictKey());
                this.insertDictionary(dictionary);
            }
        });
    }

    /**
     * 删除字典
     *
     * @param dictId 主键
     */
    @Override
    @Transactional
    public void deleteDictionary(Integer dictId) {
        Dictionary dictionary = this.getById(dictId);
        Assert.notNull(dictionary, "字典项不存在");
        LambdaQueryWrapper<Dictionary> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dictionary::getDictId, dictId);
        if (dictionary.getIsGroup()) {
            wrapper.or().eq(Dictionary::getGroupId, dictId);
        }
        this.baseMapper.delete(wrapper);
    }

    /**
     * 删除字典
     *
     * @param dictId 主键集
     */
    @Override
    @Transactional
    public void deleteDictionary(Set<Integer> dictId) {
        LambdaQueryWrapper<Dictionary> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Dictionary::getDictId, dictId);
        this.baseMapper.delete(wrapper);
    }
}