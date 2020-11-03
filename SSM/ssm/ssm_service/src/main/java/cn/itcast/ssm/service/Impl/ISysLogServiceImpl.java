package cn.itcast.ssm.service.Impl;

import cn.itcast.ssm.dao.ISyslogDao;
import cn.itcast.ssm.domain.Syslog;
import cn.itcast.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ISysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISyslogDao syslogDao;

    @Override
    public void save(Syslog syslog) {
            syslogDao.save(syslog);
    }

    @Override
    public List<Syslog> findAll() {
        return syslogDao.findAll();
    }
}
