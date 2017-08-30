package com.david.sys.entity;

import java.util.Date;

/**
 * @author David
 */
public class HomeworkComment extends DataEntity<HomeworkComment> {

    private String homeworkId;
    private String content;
    private Date time;
    private String UserName;

    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
