import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.mybatis.Sqlsession.SqlSession;
import com.itheima.mybatis.Sqlsession.SqlSessionFactory;
import com.itheima.mybatis.Sqlsession.SqlSessionFactoryBuilder;
import com.itheima.mybatis.io.Resources;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    public static void main(String[] args) throws IOException {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactoryBuilder的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.使用构建者创建工厂对象SqlSessionFactory;
        SqlSessionFactory factory = builder.build(in);
        //4.使用SqlSessionFactory生产SqlSession对象
        SqlSession session = factory.openSession();
        //5.使用SqlSession创建dao接口代理对象
        UserDao userdao = session.getMapper(UserDao.class);

        List<User> all = userdao.findAll();
        for (User user : all) {
            System.out.println(user);
        }

    }
}
