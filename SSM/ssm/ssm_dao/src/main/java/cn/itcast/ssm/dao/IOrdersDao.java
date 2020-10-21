package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Orders;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersDao {

    @Select("select * from orders")
    @Results({

    })


    List<Orders> findAll() throws Exception;
}
