package org.ulplatform.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-7-27.
 */
public class UauthInfo implements Serializable {
    private static final long serialVersionUID = -190754310746841748L;
    private String systemId;
    private String token;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
