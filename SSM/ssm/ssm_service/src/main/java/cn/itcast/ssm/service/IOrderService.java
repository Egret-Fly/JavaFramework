package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Orders;

import java.util.List;

public interface IOrderService {

    List<Orders> findAll(int page,int size) throws Exception;

    Orders findById(String id);
}
