package org.ulplatform.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-7-14.
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -190734710746841476L;
    private String userName;
    private String userid;
    private String name;
    private String deptcode;
    private String department;
    private String telephone;
    private String systemId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", deptcode='" + deptcode + '\'' +
                ", department='" + department + '\'' +
                ", telephone='" + telephone + '\'' +
                ", systemId='" + systemId + '\'' +
                '}';
    }
}
