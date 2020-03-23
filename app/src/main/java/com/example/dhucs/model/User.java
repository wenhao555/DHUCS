package com.example.dhucs.model;

import java.util.List;

public class User
{
    // 唯一的id
    private int id;
    // 报名的user
    private List<User> activityUserList;
    // 活动内容
    private String content;
    // 活动风采
    private String happyContent;
    // 活动管理员
    private User activityAdminUserList;
    //建议内容
    private List<String> suggestList;
    // 签退的user
    private User signOffUser;
}
