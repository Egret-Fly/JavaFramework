package com.itheima.jdbctemple;

import com.itheima.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * JdbcTemplate的crud操作
 */
public class JdbcTemplateDemo3 {

    public static void main(String[] args) {
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.获取对象
        JdbcTemplate jt  = ac.getBean("jdbcTemplate",JdbcTemplate.class);
        //3.执行操作
        //保存
   //     jt.update("insert into account(name,money)values (?,?)","eee",333f);
        //更新
   //     jt.update("update  account set name = ?,money = ? where id=?","test",333f,6);
        //删除
   //     jt.update("delete from account where id = ?",8)
        //查询所有
        //List<Account> accounts = jt.query("select * from account where money > ?",new AccountRowMapper(),1000);
        List<Account> accounts = jt.query("select * from account where money > ?",new BeanPropertyRowMapper<Account>(Account.class),1000);
        for (Account account : accounts) {
            System.out.println(account);
        }
        //查询一个
        //查询返回一行一列
    }
}

/**
 * 定义Account的封装策略
 */
class AccountRowMapper implements RowMapper<Account>{
    /**
     * 把结果集中的数据封装到Account中，然后由spring把每个结果Account加到集合中
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setName(resultSet.getString("name"));
        account.setMoney(resultSet.getFloat("money"));
        return account;
    }
}