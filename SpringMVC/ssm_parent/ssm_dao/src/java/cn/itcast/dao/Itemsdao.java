package cn.itcast.dao;

import cn.itcast.domain.Items2;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface Itemsdao {
    @Select("select * from items where id = #{id}")
    Items2 findById(Integer id);
}
