package cn.md.trainclient.model;

import java.util.Date;

/**
 * Created by jindongping on 15/7/24.
 */
public class CoursewareQuerryItem {

    private int coursewareID;
    private String coursewareName;
    private int coursewareFileTypeID;
    private String coursewareURL;
    private int coursewareCreatorID;
    private String coursewareCreatorName;
    private Date coursewareCreateTime;
    private int modifyPersonID;
    private String modifyPersonName;
    private Date modifyTime;
    private String twoDimensionCode;
    private boolean isDeleted;
    private String notes;

    public int getCoursewareID() {
        return coursewareID;
    }

    public void setCoursewareID(int coursewareID) {
        this.coursewareID = coursewareID;
    }

    public String getCoursewareName() {
        return coursewareName;
    }

    public void setCoursewareName(String coursewareName) {
        this.coursewareName = coursewareName;
    }

    public int getCoursewareFileTypeID() {
        return coursewareFileTypeID;
    }

    public void setCoursewareFileTypeID(int coursewareFileTypeID) {
        this.coursewareFileTypeID = coursewareFileTypeID;
    }

    public String getCoursewareURL() {
        return coursewareURL;
    }

    public void setCoursewareURL(String coursewareURL) {
        this.coursewareURL = coursewareURL;
    }

    public int getCoursewareCreatorID() {
        return coursewareCreatorID;
    }

    public void setCoursewareCreatorID(int coursewareCreatorID) {
        this.coursewareCreatorID = coursewareCreatorID;
    }

    public String getCoursewareCreatorName() {
        return coursewareCreatorName;
    }

    public void setCoursewareCreatorName(String coursewareCreatorName) {
        this.coursewareCreatorName = coursewareCreatorName;
    }

    public Date getCoursewareCreateTime() {
        return coursewareCreateTime;
    }

    public void setCoursewareCreateTime(Date coursewareCreateTime) {
        this.coursewareCreateTime = coursewareCreateTime;
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

    public String getTwoDimensionCode() {
        return twoDimensionCode;
    }

    public void setTwoDimensionCode(String twoDimensionCode) {
        this.twoDimensionCode = twoDimensionCode;
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
