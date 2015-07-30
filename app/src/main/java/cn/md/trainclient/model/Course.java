package cn.md.trainclient.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jindongping on 15/7/23.
 */
public class Course implements Serializable{
    private int courseID;
    private int courseProfessionalTypeClassID;
    private String courseName;
    private String courseDescribe;
    private int courseValuation;
    private Date courseCreateTime;
    private int courseCreatorID;
    private String courseCreatorName;
    private int modifyPersonID;
    private String modifyPersonName;
    private Date modifyTime;
    private boolean isDeleted;
    private String notes;

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCourseProfessionalTypeClassID() {
        return courseProfessionalTypeClassID;
    }

    public void setCourseProfessionalTypeClassID(int courseProfessionalTypeClassID) {
        this.courseProfessionalTypeClassID = courseProfessionalTypeClassID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescribe() {
        return courseDescribe;
    }

    public void setCourseDescribe(String courseDescribe) {
        this.courseDescribe = courseDescribe;
    }

    public int getCourseValuation() {
        return courseValuation;
    }

    public void setCourseValuation(int courseValuation) {
        this.courseValuation = courseValuation;
    }

    public Date getCourseCreateTime() {
        return courseCreateTime;
    }

    public void setCourseCreateTime(Date courseCreateTime) {
        this.courseCreateTime = courseCreateTime;
    }

    public int getCourseCreatorID() {
        return courseCreatorID;
    }

    public void setCourseCreatorID(int courseCreatorID) {
        this.courseCreatorID = courseCreatorID;
    }

    public String getCourseCreatorName() {
        return courseCreatorName;
    }

    public void setCourseCreatorName(String courseCreatorName) {
        this.courseCreatorName = courseCreatorName;
    }

    public int getModifyPersonID() {
        return modifyPersonID;
    }

    public void setModifyPersonID(int modifyPersonID) {
        this.modifyPersonID = modifyPersonID;
    }

    public String getModifyPersonName() {
        return modifyPersonName;
    }

    public void setModifyPersonName(String modifyPersonName) {
        this.modifyPersonName = modifyPersonName;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
