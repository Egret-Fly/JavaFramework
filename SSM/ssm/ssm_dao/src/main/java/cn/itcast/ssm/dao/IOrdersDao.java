package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.domain.Product;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
        @Result(id=true,property="id",column="id"),
        @Result(property="orderNum",column="orderNum"),
        @Result(property="productName",column="productName"),
        @Result(property="orderStatus",column="orderStatus"),
        @Result(property="peopleCount",column="peopleCount"),
        @Result(property="payType",column="payType"),
        @Result(property="product",column="productId",javaType= Product.class,one = @One(select = "com.itheima.ssm.dao.IProductDao.findById")),


    })
    List<Orders> findAll() throws Exception;
}
