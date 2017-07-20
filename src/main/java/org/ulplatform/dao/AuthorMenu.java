package org.ulplatform.dao;

import java.math.BigDecimal;

public class AuthorMenu {
    private BigDecimal menuid;

    private String systemname;

    private String firstmenu;

    private String secondmenu;

    private String thirstmenu;

    private String forthmenu;

    private String url;

    private String username;

    private String userid;

    public BigDecimal getMenuid() {
        return menuid;
    }

    public void setMenuid(BigDecimal menuid) {
        this.menuid = menuid;
    }

    public String getSystemname() {
        return systemname;
    }

    public void setSystemname(String systemname) {
        this.systemname = systemname == null ? null : systemname.trim();
    }

    public String getFirstmenu() {
        return firstmenu;
    }

    public void setFirstmenu(String firstmenu) {
        this.firstmenu = firstmenu == null ? null : firstmenu.trim();
    }

    public String getSecondmenu() {
        return secondmenu;
    }

    public void setSecondmenu(String secondmenu) {
        this.secondmenu = secondmenu == null ? null : secondmenu.trim();
    }

    public String getThirstmenu() {
        return thirstmenu;
    }

    public void setThirstmenu(String thirstmenu) {
        this.thirstmenu = thirstmenu == null ? null : thirstmenu.trim();
    }

    public String getForthmenu() {
        return forthmenu;
    }

    public void setForthmenu(String forthmenu) {
        this.forthmenu = forthmenu == null ? null : forthmenu.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }
}