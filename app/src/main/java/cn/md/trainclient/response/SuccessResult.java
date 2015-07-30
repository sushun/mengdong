package cn.md.trainclient.response;

/**
 * Author: sushun
 * Date: 2015-07-09.
 */
public class SuccessResult extends RequestResult {
    private static final long serialVersionUID = 7273263515556341174L;

    private Response response;

    public SuccessResult(Response respones) {
        this.response = respones;
    }

    public Response getResponse() {
        return response;
    }
}
