package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.Userdao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import com.alibaba.druid.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserdaoImpl implements Userdao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findByUsername(String username) {
        User user = null;
        try{
        //1.定义sql
        String sql = "select * from tab_user where username = ?";
        user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);}
        catch (Exception e){

        }

        //2.执行sql
        return user;
    }

    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code)"+
                "values (?,?,?,?,?,?,?,?,?)";
        template.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),user.getStatus(),user.getCode());
    }

    @Override
    public User findByCode(String code) {
        User user = null;
        try{
            String sql = "select * from tab_user where code = ?";
            user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),code);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updataStatus(User u) {
        String sql = "update tab_user set status = 'Y' where uid = ?";
        template.update(sql,u.getUid());
    }

    @Override
    public User findByUsernameAndPassword(String username,String password) {
        User user = null;
        try{
            //1.定义sql
            String sql = "select * from tab_user where username = ? and password = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);}
        catch (Exception e){

        }

        //2.执行sql
        return user;

    }
}
