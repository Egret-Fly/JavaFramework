package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    //根据用户id查询出所有对应的角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,property = "id" ,column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions" , column = "id" ,javaType = java.util.List.class,many = @Many(select = "cn.itcast.ssm.dao.IPermissionDao.findPermissionbyroles"))
    })
    public List<Role> findRoleByUserId(String userId);

    @Select("select * from role")
    public List<Role> findAll();

    @Insert("Insert into role(id,roleName,roleDesc) values(#{id},#{roleName},#{roleDesc})")
    public void save(Role role);


    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermission(String roleId);

    @Select("select * from role where id=#{id}")
    Role findById(String roleId);


    @Insert("insert into role_permission values(#{permissionId},#{roleId})")
    void addRoleToUser(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
