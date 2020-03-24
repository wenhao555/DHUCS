package com.example.dhucs.model;

import java.io.Serializable;
import java.util.List;

public class Activities implements Serializable
{
    // 唯一的id
    private int id;
    // 报名的user
    private List<User> activityUserList;
    private String image;
    private String title;
    // 活动内容
    private String content;
    // 活动管理员
    private User activityAdminUser;
    //建议内容
    private List<String> suggestList;
    // 签退的user
    private User signOffUser;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public List<User> getActivityUserList()
    {
        return activityUserList;
    }

    public void setActivityUserList(List<User> activityUserList)
    {
        this.activityUserList = activityUserList;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public User getActivityAdminUser()
    {
        return activityAdminUser;
    }

    public void setActivityAdminUser(User activityAdminUser)
    {
        this.activityAdminUser = activityAdminUser;
    }

    public List<String> getSuggestList()
    {
        return suggestList;
    }

    public void setSuggestList(List<String> suggestList)
    {
        this.suggestList = suggestList;
    }

    public User getSignOffUser()
    {
        return signOffUser;
    }

    public void setSignOffUser(User signOffUser)
    {
        this.signOffUser = signOffUser;
    }
}
