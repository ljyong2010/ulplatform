package org.ulplatform.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-7-14.
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -190734710746841476L;
    private String userName;
    private String name;
    private String department;
    private String telephone;
    private String ctype;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", telephone='" + telephone + '\'' +
                ", ctype='" + ctype + '\'' +
                '}';
    }
}
