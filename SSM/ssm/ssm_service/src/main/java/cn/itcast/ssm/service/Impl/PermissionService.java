package cn.itcast.ssm.service.Impl;

import cn.itcast.ssm.dao.IPermissionDao;
import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.service.IPermisssionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionService implements IPermisssionService {

   @Autowired
   private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}
