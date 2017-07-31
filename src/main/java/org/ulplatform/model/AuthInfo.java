package org.ulplatform.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-7-27.
 */
public class AuthInfo implements Serializable {
    private static final long serialVersionUID = -190734310746841748L;
    private String systemId;
    private String userId;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
