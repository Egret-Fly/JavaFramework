package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("select  * from permission where id in (select permissionId from role_permission where roleId = #{id} )")
    public List<Permission> findPermissionbyroles(String id);

    @Select("select * from permission")
    public List<Permission> findAll();


    @Insert("insert into permission values(#{id},#{permissionName},#{permissionName})")
    public void save(Permission permission);
}
