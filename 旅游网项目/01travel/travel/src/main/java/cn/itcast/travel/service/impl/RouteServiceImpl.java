package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.Userdao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgImp;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private RouteDao routedao  = new RouteDaoImpl();
    private RouteImgDao routeImgda  = new RouteImgImp() ;
    private SellerDao sellerdao  = new SellerDaoImpl() ;
    @Override
    public PageBean<Route> pageQuery(int currentPage, int pageSize, int cid, String cname) {
        //封装PageBean
        PageBean<Route> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);

        pb.setPageSize(pageSize);
        int start = (currentPage-1)*pageSize;

        List<Route> list= routedao.findByPage(cid,start,pageSize,cname);
        pb.setList(list);

        int findtotalcount = routedao.findtotalcount(cid,cname);
        pb.setTotalCount(findtotalcount);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = findtotalcount % pageSize == 0 ? findtotalcount / pageSize :(findtotalcount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);

        return pb;

    }

    @Override
    public Route findOne(String rid) {
        Route route = routedao.findOne(Integer.parseInt(rid));

        //2.根据route的id 查询图片集合信息
        List<RouteImg> routeImgList = routeImgda.findByRid(route.getRid());
        route.setRouteImgList(routeImgList);

        Seller seller = sellerdao.findById(route.getSid());
        route.setSeller(seller);

        return route;

    }


}
