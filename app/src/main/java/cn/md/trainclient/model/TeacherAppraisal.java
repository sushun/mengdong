package cn.md.trainclient.model;

import java.io.Serializable;

/**
 * User: sush
 * Date: 2015-07-30.
 */
public class TeacherAppraisal implements Serializable {
    private static final long serialVersionUID = 6914133348985403427L;

    private String content;
    private String teacherName;
    private long time;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
