package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private  RouteService routeService = new RouteServiceImpl();
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        //接受rname
        String rname = request.getParameter("rname");
        if (rname!=null){
        rname = new String(rname.getBytes("iso-8859-1"),"utf-8");}


        int currentPage = 0;
        if (currentPageStr != null&&currentPageStr.length()>0){
            currentPage = Integer.parseInt(currentPageStr);
        }else {
            currentPage=1;
        }

        int pageSize = 0;
        if (pageSizeStr != null&&pageSizeStr.length()>0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize=5;
        }

        int cid = 0;
        if (cidStr != null&&cidStr.length()>0&&!"null".equals(cidStr)){
            cid = Integer.parseInt(cidStr);
        }else {
            cid=1;
        }


        PageBean<Route> pb = routeService.pageQuery(currentPage, pageSize, cid,rname);
        writeVale(pb,response);


    }
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String rid = request.getParameter("rid");

        Route route= routeService.findOne(rid);
        writeVale(route,response);

    }

}
