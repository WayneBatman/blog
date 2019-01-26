package com.wayne.bean;

import java.io.Serializable;
import java.util.List;

public class ClassMainProperty implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String packageName;
    private String className;

    private List<ClassColumn> classColumns;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ClassColumn> getClassColumns() {
        return classColumns;
    }

    public void setClassColumns(List<ClassColumn> classColumns) {
        this.classColumns = classColumns;
    }
}
