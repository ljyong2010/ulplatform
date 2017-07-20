package org.ulplatform.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-7-14.
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -190734710746841476L;
    private String userid;
    private String cid;
    private String userName;
    private String passWord;
    private String type;
    private String redirectUrl;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userid='" + userid + '\'' +
                ", cid='" + cid + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", type='" + type + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                '}';
    }
}
