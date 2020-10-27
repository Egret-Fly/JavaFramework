package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

public interface IUserDao {

    @Select("select * from users where username=#{username} ")
    public UserInfo findByUsername(String username) throws Exception;

}
