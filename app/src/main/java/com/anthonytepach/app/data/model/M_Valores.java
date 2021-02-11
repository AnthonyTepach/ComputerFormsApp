package com.anthonytepach.app.data.model;

public class M_Valores {

    private String name;
    private String desc;

    public M_Valores(String name, String desc) {
        this.name = name;
        this.desc = desc;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
