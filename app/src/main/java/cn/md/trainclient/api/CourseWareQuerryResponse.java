package cn.md.trainclient.api;

import cn.md.trainclient.model.CoursewareQuerryItem;
import cn.md.trainclient.response.ApiResponse;

import java.util.List;

public class CourseWareQuerryResponse extends ApiResponse {
    //解决服务器返回字段，最外层无名字list的Gson bug ，手动加入名字 noTitleList
    private List<CoursewareQuerryItem> noTitleList;

    public CourseWareQuerryResponse(List<CoursewareQuerryItem> noTitleList) {
        this.noTitleList = noTitleList;
    }

    /**
     * @return Gson解析后的对象
     */
    public List<CoursewareQuerryItem> getData() {
        return noTitleList;
    }

}
