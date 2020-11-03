package cn.itcast.ssm.service.Impl;

import cn.itcast.ssm.dao.IRoleDao;
import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public List<Permission> findOtherPermission(String roleId) {
        return roleDao.findOtherPermission(roleId);
    }

    @Override
    public Role findById(String roleId) {
        return roleDao.findById(roleId);
    }

    @Override
    public void addRoleToUser(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.addRoleToUser(roleId,permissionId);
        }
    }
}
