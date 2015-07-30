package cn.md.trainclient.request;

import java.io.Serializable;

public abstract class Request implements Serializable {
    private static final long serialVersionUID = 7422168166452799039L;

    public boolean isDuplicated(Request other) {
        return getClass() == other.getClass();
    }
}
