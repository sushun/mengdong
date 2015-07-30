package cn.md.trainclient.model;

import java.io.Serializable;

/**
 * User: su
 * Date: 2015-07-13.
 */
public class ExamAnswer implements Serializable {
    private static final long serialVersionUID = -7625166021664200687L;

    private String content;
    private boolean isRight;
    private boolean isSelected;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean isRight) {
        this.isRight = isRight;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
