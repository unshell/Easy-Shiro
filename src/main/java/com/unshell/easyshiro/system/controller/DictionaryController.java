package com.unshell.easyshiro.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.unshell.easyshiro.common.annotation.ControllerEndpoint;
import com.unshell.easyshiro.common.controller.BaseController;
import com.unshell.easyshiro.common.entity.EasyResponse;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.system.entity.Dictionary;
import com.unshell.easyshiro.system.service.IDictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/dict")
@RequiredArgsConstructor
public class DictionaryController extends BaseController {
    private final IDictionaryService dictionaryService;

    /**
     * 字典分页信息
     *
     * @param request
     * @param dictionary
     */
    @GetMapping("page")
    public EasyResponse dictionaryPage(QueryRequest request, Dictionary dictionary) {
        IPage<Dictionary> page = dictionaryService.queryDictionaryPage(request, dictionary);
        return new EasyResponse().table(page);
    }

    /**
     * 字典组关联的字典项信息
     *
     * @param groupKey 字典组键
     */
    @GetMapping("map")
    public EasyResponse dictionaryMap(String groupKey) {
        Map<String, String> maps = dictionaryService.queryDictionaryMap(groupKey);
        return new EasyResponse().success().data(maps);
    }

    /**
     * 字典组信息
     *
     * @param dictionary
     */
    @GetMapping("group")
    public EasyResponse dictionaryGroup(Dictionary dictionary) {
        List<Dictionary> group = dictionaryService.queryDictionaryGroupList(dictionary);
        return new EasyResponse().put("code", 0).data(group);
    }

    /**
     * 创建字典
     *
     * @param dictionary
     */
    @PostMapping("add")
    @ControllerEndpoint(operation = "创建了字典")
    public EasyResponse dictAdd(Dictionary dictionary) {
        dictionaryService.insertDictionary(dictionary);
        return new EasyResponse().success().message("字典创建成功");
    }

    /**
     * 更新字典
     *
     * @param dictionary
     */
    @PostMapping("update")
    @ControllerEndpoint(operation = "更新了字典")
    public EasyResponse dictUpdate(Dictionary dictionary) {
        dictionaryService.updateDictionary(dictionary);
        return new EasyResponse().success().message("更新字典成功");
    }

    /**
     * 删除字典组
     *
     * @param dictId 主键
     */
    @PostMapping("group/delete")
    @ControllerEndpoint(operation = "删除了字典组")
    public EasyResponse groupDelete(Integer dictId) {
        dictionaryService.deleteDictionary(dictId);
        return new EasyResponse().success().message("删除字典成功");
    }

    /**
     * 删除字典项
     *
     * @param dictId 主键集
     */
    @PostMapping("delete")
    @ControllerEndpoint(operation = "创建了字典项")
    public EasyResponse dictDelete(@RequestParam(value = "dictId[]") Set<Integer> dictId) {
        dictionaryService.deleteDictionary(dictId);
        return new EasyResponse().success().message("删除字典成功");
    }
}