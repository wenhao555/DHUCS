package com.example.dhucs.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable
{
    private int id;
    private String account;
    private String password;
    private String name;
    private String sex;
    private String birth;
    private Boolean admin;
    private String image;

    // 已报名的活动
    private List<Integer> signActivityList;
    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getBirth()
    {
        return birth;
    }

    public void setBirth(String birth)
    {
        this.birth = birth;
    }

    public Boolean getAdmin()
    {
        return admin;
    }

    public void setAdmin(Boolean admin)
    {
        this.admin = admin;
    }

    public List<Integer> getSignActivityList()
    {
        return signActivityList;
    }

    public void setSignActivityList(List<Integer> signActivityList)
    {
        this.signActivityList = signActivityList;
    }

}
