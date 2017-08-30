package com.david.sys.entity;

import java.util.Date;

/**
 * @author David
 */
public class Homework extends DataEntity<Homework> {

    private String title;
    private String content;
    private Date deadline;
    private String deadlineStr;
    private String hasUpload;

    public String getDeadlineStr() {
        return deadlineStr;
    }

    public void setDeadlineStr(String deadlineStr) {
        this.deadlineStr = deadlineStr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHasUpload() {
        return hasUpload;
    }

    public void setHasUpload(String hasUpload) {
        this.hasUpload = hasUpload;
    }
}
