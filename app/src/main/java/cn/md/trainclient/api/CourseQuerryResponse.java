package cn.md.trainclient.api;

import cn.md.trainclient.model.Course;
import cn.md.trainclient.response.ApiResponse;

import java.util.List;

public class CourseQuerryResponse extends ApiResponse {
    //解决服务器返回字段，最外层无名字list的Gson bug ，手动加入名字 noTitleList
    private List<Course> noTitleList;

    public CourseQuerryResponse(List<Course> noTitleList) {
        this.noTitleList = noTitleList;
    }

    /**
     * @return Gson解析后的对象
     */
    public List<Course> getData() {
        return noTitleList;
    }

}
