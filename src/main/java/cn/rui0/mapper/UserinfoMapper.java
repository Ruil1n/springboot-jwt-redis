package cn.rui0.mapper;

import cn.rui0.model.Userinfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserinfoMapper {

    //根据用户名和密码查找
    Userinfo findByUserNameAndPassword(@Param("username")String username, @Param("password")String password);

    int deleteByPrimaryKey(Integer id);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);
}