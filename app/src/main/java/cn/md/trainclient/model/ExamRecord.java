package cn.md.trainclient.model;

import java.io.Serializable;

/**
 * User: sush
 * Date: 2015-07-30.
 */
public class ExamRecord implements Serializable {
    private static final long serialVersionUID = 732889373432498731L;

    private int score;
    private long date;
    private long duration;
    private String courseId;
    private String courseName;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
