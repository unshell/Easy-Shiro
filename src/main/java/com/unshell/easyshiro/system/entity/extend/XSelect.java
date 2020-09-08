package com.unshell.easyshiro.system.entity.extend;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class XSelect implements Serializable {
    private String name;
    private Object value;
    private List<XSelect> children;
    private Boolean selected;
    private Boolean disabled;

    public XSelect(String _name, Object _value, Integer _key) {
        this.name = _name;
        this.value = _value;
        if (_key != null && _key.equals(_value)) {
            this.selected = true;
        }
    }

    public XSelect(String _name, Object _value, Boolean _selected, Boolean _disabled) {
        this.name = _name;
        this.value = _value;
        this.selected = _selected;
        this.disabled = _disabled;
    }
}