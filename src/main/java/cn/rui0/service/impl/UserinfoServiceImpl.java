package cn.rui0.service.impl;

import cn.rui0.mapper.UserinfoMapper;
import cn.rui0.model.Userinfo;
import cn.rui0.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Ruilin on 2018/4/19.
 */
@Service
@Transactional
public class UserinfoServiceImpl implements UserinfoService {
    @Autowired
    private UserinfoMapper userinfoMapper;

    /**
     * 登录
     * 根据用户名和密码进行查询
     */
    @Override
    public Userinfo login(String username, String password) {
        return userinfoMapper.findByUserNameAndPassword(username, password);
    }

    /**
     * 注册
     * 增加用户
     */
    @Override
    public int register(Userinfo user) {
        return 0;
    }

    /**
     * 根据用户名查询
     */
    @Override
    public Userinfo findByUserName(String username) {
        return null;
    }

    @Override
    public Userinfo findByUserId(int id) {
        return userinfoMapper.selectByPrimaryKey(id);
    }
}
