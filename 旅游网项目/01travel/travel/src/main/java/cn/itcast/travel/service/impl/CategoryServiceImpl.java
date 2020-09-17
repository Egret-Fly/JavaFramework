package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.Categorydao;
import cn.itcast.travel.dao.impl.CategorydaoImp;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private Categorydao cg = new CategorydaoImp();
    @Override
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis();
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);

        List<Category> cs=null;
        if(categorys == null || categorys.size()==0){
            System.out.println("从数据库查询");
            cs= cg.findAll();
            for(int i=0;i<cs.size();i++) {
                jedis.zadd("category", cs.get(i).getCid(), cs.get(i).getCname());
            }
        }else{
            System.out.println("从redis中查询");
            cs = new ArrayList<Category>();
            for (Tuple c: categorys) {
                Category cg= new Category();
                cg.setCname(c.getElement());
                cg.setCid((int)c.getScore());
                cs.add(cg);
            }

        }

        return cs;

    }
}
