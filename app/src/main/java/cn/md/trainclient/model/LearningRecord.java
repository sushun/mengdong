package cn.md.trainclient.model;

import java.io.Serializable;

/**
 * User: sush
 * Date: 2015-07-30.
 */
public class LearningRecord implements Serializable {
    private static final long serialVersionUID = 235955720765323734L;

    private String courseName;
    private long learningDate;
    private int courseType;
    private float progress;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public long getLearningDate() {
        return learningDate;
    }

    public void setLearningDate(long learningDate) {
        this.learningDate = learningDate;
    }

    public int getCourseType() {
        return courseType;
    }

    public void setCourseType(int courseType) {
        this.courseType = courseType;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}
