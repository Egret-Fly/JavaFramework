package com.itheima.jdbctemple;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcTemplate最基本对象
 */
public class JdbcTemplateDemo1 {

    public static void main(String[] args) {
        //1.创建JdbcTemplate对象
        JdbcTemplate jt = new JdbcTemplate();
        //2.执行操作
        jt.execute("insert into account(name,money)values ('ccc',1000)");
    }
}
