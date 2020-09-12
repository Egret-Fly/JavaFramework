package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    public int findtotalcount(int cid,String cname);

    public List<Route> findByPage(int cid,int start,int pageSize,String cname);

    Route findOne(int parseInt);
}
