package cn.rui0.model;

import org.springframework.stereotype.Component;

/**
 * Created by Ruilin on 2018/4/10.
 */
@Component
public class TokenModel {
    //用户id
    private long userId;

    private String userName;

    private String token;

//    public TokenModel(long userId, String userName,String token) {
//        this.userId = userId;
//        this.userName=userName;
//        this.token = token;
//    }

    public void setTokenModel(long userId, String userName,String token){
        this.userId = userId;
        this.userName=userName;
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
