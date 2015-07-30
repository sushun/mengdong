package cn.md.trainclient.model;

import java.io.Serializable;

/**
 * User: su
 * Date: 2015-07-11.
 */
public class ExamQuestion implements Serializable {
    private static final long serialVersionUID = -3365065112537971213L;

    private String content;
    private int questionType;
    private int rightIndex;
    private boolean isCollected;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public int getRightIndex() {
        return rightIndex;
    }

    public void setRightIndex(int rightIndex) {
        this.rightIndex = rightIndex;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }
}
