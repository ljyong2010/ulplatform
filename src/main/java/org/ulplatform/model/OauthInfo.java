package org.ulplatform.model;

import java.io.Serializable;

public class OauthInfo implements Serializable {
    private static final long serialVersionUID = -190734310746841478L;
    private String systemId;
    private String phone;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
