package cn.rui0.controller;

import cn.rui0.model.Userinfo;
import cn.rui0.security.annotation.CurrentUser;
import cn.rui0.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ruilin on 2018/4/19.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserinfoService userinfoService;


    @GetMapping("/show")
    public Userinfo Show(@CurrentUser Userinfo userinfo){

        return userinfo;
    }
}
