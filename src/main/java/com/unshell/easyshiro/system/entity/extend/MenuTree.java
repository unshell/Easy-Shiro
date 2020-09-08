package com.unshell.easyshiro.system.entity.extend;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.unshell.easyshiro.system.entity.Menu;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuTree {
    @JsonIgnore
    private Integer id;
    @JsonIgnore
    private Integer parentId;
    private String name;
    private String url;
    private String icon;
    private List<MenuTree> subMenus;

    public MenuTree(Integer _id, Integer _parentId, String _name, String _url, String _icon) {
        this.id = _id;
        this.parentId = _parentId;
        this.name = _name;
        this.url = StringUtils.isNotBlank(_url) ? _url : "javascript:;";
        this.icon = _icon;
    }

    public static List<MenuTree> getTree(List<MenuTree> list) {
        ArrayList<MenuTree> trees = new ArrayList<>();
        list.forEach(x -> {
            if (x.getParentId().equals(Menu.TOP_NODE_ID)) {
                trees.add(x);
            }
        });
        trees.forEach(x -> {
            List<MenuTree> nodes = getNode(x.getId(), list);
            x.setSubMenus(nodes);
        });
        return trees;
    }

    public static List<MenuTree> getNode(int id, List<MenuTree> trees) {
        ArrayList<MenuTree> nodes = new ArrayList<>();
        trees.forEach(x -> {
            if (x.getParentId().equals(id)) {
                nodes.add(x);
            }
        });
        nodes.forEach(x -> {
            x.setSubMenus(getNode(x.getId(), trees));
        });
        return nodes.size() > 0 ? nodes : null;
    }
}