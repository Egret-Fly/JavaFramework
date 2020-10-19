package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductDao {

    //查询所有产品信息
    public List<Product> findAll() throws Exception;
}
