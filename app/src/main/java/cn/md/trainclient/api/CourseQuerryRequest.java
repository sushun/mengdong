package cn.md.trainclient.api;

import cn.md.trainclient.request.ApiRequest;

public class CourseQuerryRequest extends ApiRequest {
    @ApiField(paramName = "coursetypeid")
    private int coursetypeid;
    @ApiField(paramName = "pageindex")
    private int pageindex;
    @ApiField(paramName = "pagesize")
    private int pagesize;

    public CourseQuerryRequest(int coursetypeid, int pageindex, int pagesize) {
        this.coursetypeid = coursetypeid;
        this.pageindex = pageindex;
        this.pagesize = pagesize;
    }
}
