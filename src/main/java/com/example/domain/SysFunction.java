package com.example.domain;

/**
 * Created by admin on 17/4/27.
 */
public class SysFunction implements java.io.Serializable{

    private int id;
    private String functionUrl;//url
    private String functionName;//资源名称

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFunctionUrl() {
        return functionUrl;
    }

    public void setFunctionUrl(String functionUrl) {
        this.functionUrl = functionUrl;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
}
