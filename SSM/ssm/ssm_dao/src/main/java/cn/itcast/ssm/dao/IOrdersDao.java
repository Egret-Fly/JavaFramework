package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Orders;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersDao {

    List<Orders> findAll() throws Exception;
}
