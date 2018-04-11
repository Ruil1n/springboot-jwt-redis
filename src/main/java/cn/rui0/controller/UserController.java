package cn.rui0.controller;


import cn.rui0.model.TokenModel;
import cn.rui0.model.User;
import cn.rui0.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public TokenModel login(User user) throws ServletException {
        String name = user.getUsername();
        String pass = user.getPassword();
        if (!"admin".equals(name)) {
            throw new ServletException("no such user");
        }
        if (!"123456".equals(pass)) {
            throw new ServletException("wrong password");
        }
        return jwtUtil.creatToken(name,1234);

    }

    @GetMapping("/success")
    public String success() {
        return "login success";
    }
}
