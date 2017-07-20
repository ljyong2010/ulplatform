package org.ulplatform.model;

import org.ulplatform.dao.DepartUser;

/**
 * Created by Administrator on 2017-7-10.
 */
public class SessionInfo {
    private String id;
    private DepartUser departUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DepartUser getDepartUser() {
        return departUser;
    }

    public void setDepartUser(DepartUser departUser) {
        this.departUser = departUser;
    }

    @Override
    public String toString() {
        return "SessionInfo{" +
                "id='" + id + '\'' +
                ", departUser=" + departUser +
                '}';
    }
}
