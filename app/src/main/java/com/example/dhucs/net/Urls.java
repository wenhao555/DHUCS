package com.example.dhucs.net;

public class Urls
{
    public static String defaultUrl = "http://192.168.1.120:8000";
    public static String login = defaultUrl + "/login";//登陆
    public static String createUser = defaultUrl + "/createUser";//注册
    public static String modifyPass = defaultUrl + "/modifyPass";//修改密码
    public static String getUserInfo = defaultUrl + "/getUserInfo";//获取用户信息
    public static String setUserInfo = defaultUrl + "/setUserInfo";//获取用户信息
    public static String getNewsByType = defaultUrl + "/getNewsByType";//根据类别获取新闻
    public static String setNewsToRecommend = defaultUrl + "/setNewsToRecommend";//设置为推荐新闻
    public static String getNewsByRecommend = defaultUrl + "/getNewsByRecommend";//查看推荐新闻
}
