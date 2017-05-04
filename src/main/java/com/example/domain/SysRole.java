package com.example.domain;

/**
 * Created by admin on 17/4/27.
 */
public class SysRole implements java.io.Serializable{

    private int id;
    private String name;//角色名称

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
