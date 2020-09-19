package jdBc;

import java.sql.*;

/**
 * 程序的耦合
 */
public class JdbcDemo1 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //1.注册驱动
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xd20192","root","root");
        //3.获取操作数据的预处理对象
        PreparedStatement pstm = conn.prepareStatement("select * from account");
        //4.执行SQl,得到结果即
        ResultSet rs = pstm.executeQuery();
        //5.遍历结果类
        while (rs.next()){
            System.out.println(rs.getString("name"));
        }
        //6.释放资源
        rs.close();
        pstm.close();
        conn.close();

    }
}
