package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Member;
import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
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
        @Result(property="product",column="productId",javaType= Product.class,one = @One(select = "cn.itcast.ssm.dao.IProductDao.findById")),


    })
    List<Orders> findAll() throws Exception;




    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id=true,property="id",column="id"),
            @Result(property="orderNum",column="orderNum"),
            @Result(property="productName",column="productName"),
            @Result(property="orderStatus",column="orderStatus"),
            @Result(property="peopleCount",column="peopleCount"),
            @Result(property="payType",column="payType"),
            @Result(property="product",column="productId",javaType= Product.class,one = @One(select = "cn.itcast.ssm.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select="cn.itcast.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id ",javaType = java.util.List.class,many = @Many(select="cn.itcast.ssm.dao.ITravellerDao.findById"))

    })
    Orders findById(String id);
}
