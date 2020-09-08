package com.unshell.easyshiro.system.entity.extend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RoleMenuTree implements Serializable {
    private String name;
    private boolean checked;
    @JsonProperty("pId")
    private int pId;
    private int id;
    private boolean open;
}