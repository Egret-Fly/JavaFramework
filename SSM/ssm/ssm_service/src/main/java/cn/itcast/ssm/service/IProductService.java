package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Product;

import java.util.List;

public interface IProductService {
    //查询所有产品信息
    public List<Product> findAll() throws Exception;
}
