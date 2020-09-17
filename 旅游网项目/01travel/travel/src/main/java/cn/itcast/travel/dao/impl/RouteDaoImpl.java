package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import cn.itcast.travel.util.JedisUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findtotalcount(int cid,String cname) {
        String sql = "select count(*) from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (cid!=0){
            sb.append("and cid = ? ");
            params.add(cid);
        }
        if (cname!=null&&cname.length()>0){
            sb.append(" and rname like ? ");
            params.add("%"+cname+"%");
        }
        String s=sb.toString();
        return template.queryForObject(s,Integer.class,params.toArray());

    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize,String cname) {
        String sql = " select * from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (cid!=0){
            sb.append("and cid = ? ");
            params.add(cid);
        }
        if (cname!=null&&cname.length()>0&&!"null".equals(cname)){
            sb.append(" and rname like ? ");
            params.add("%"+cname+"%");
        }
        sb.append(" limit ? , ? ");
        params.add(start);
        params.add(pageSize);
        String s = sb.toString();
        return template.query(s, new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
    }

    @Override
    public Route findOne(int parseInt) {
        String sql = "select * from tab_route where rid = ? ";
        return  template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),parseInt);
    }


}
