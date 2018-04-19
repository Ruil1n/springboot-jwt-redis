package cn.rui0.service;

import cn.rui0.model.Userinfo;

/**
 * Created by Ruilin on 2018/4/19.
 */
public interface UserinfoService {
    //通过用户名及密码核查用户登录
    public Userinfo login(String username, String password);
    //增加用户
    public int register(Userinfo user);
    //根据用户名查询
    public Userinfo findByUserName(String username);

    public Userinfo findByUserId(int id);
}
