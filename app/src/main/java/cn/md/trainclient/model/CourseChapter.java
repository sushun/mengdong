package cn.md.trainclient.model;

import java.io.Serializable;

/**
 * User: sush
 * Date: 2015-07-31.
 */
public class CourseChapter implements Serializable {
    private static final long serialVersionUID = 7825982723183911074L;

    private String courseName;
    private int questionNum;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }
}
