package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Permission;

import java.util.List;

public interface IPermisssionService {

    public List<Permission> findAll();

    public void save(Permission permission);
}
