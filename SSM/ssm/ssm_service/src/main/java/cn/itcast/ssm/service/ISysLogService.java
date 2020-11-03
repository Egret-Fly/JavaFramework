package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Syslog;

import java.util.List;

public interface ISysLogService {

    public void save(Syslog syslog);

    List<Syslog> findAll();
}
