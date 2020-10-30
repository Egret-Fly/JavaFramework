package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRoleService {

    public List<Role> findAll();

    public void save(Role role);
}
