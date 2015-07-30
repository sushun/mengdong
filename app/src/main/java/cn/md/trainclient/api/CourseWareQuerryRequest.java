package cn.md.trainclient.api;

import cn.md.trainclient.request.ApiRequest;

/**
 * Created by jindongping on 15/7/24.
 */
public class CourseWareQuerryRequest extends ApiRequest{
    @ApiField(paramName = "courseid")
    private int courseid;
    @ApiField(paramName = "pageindex")
    private int pageindex;
    @ApiField(paramName = "pagesize")
    private int pagesize;

    public CourseWareQuerryRequest(int courseid, int pageindex, int pagesize) {
        this.courseid = courseid;
        this.pageindex = pageindex;
        this.pagesize = pagesize;
    }
}
