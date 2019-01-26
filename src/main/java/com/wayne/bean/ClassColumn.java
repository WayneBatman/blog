package com.wayne.bean;

import java.io.Serializable;

public class ClassColumn implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String columnName;
    private String columnType;
    private Integer columnLength;
    private Integer columnPrecision;
    private Integer columnScale;
    private String columnComment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public Integer getColumnLength() {
        return columnLength;
    }

    public void setColumnLength(Integer columnLength) {
        this.columnLength = columnLength;
    }

    public Integer getColumnPrecision() {
        return columnPrecision;
    }

    public void setColumnPrecision(Integer columnPrecision) {
        this.columnPrecision = columnPrecision;
    }

    public Integer getColumnScale() {
        return columnScale;
    }

    public void setColumnScale(Integer columnScale) {
        this.columnScale = columnScale;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }
}
