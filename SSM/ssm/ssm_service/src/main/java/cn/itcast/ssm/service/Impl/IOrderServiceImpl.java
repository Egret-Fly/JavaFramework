package cn.itcast.ssm.service.Impl;

import cn.itcast.ssm.dao.IOrdersDao;
import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class IOrderServiceImpl implements IOrderService {

    @Autowired
    private IOrdersDao ordersDao;


    @Override
    public List<Orders> findAll() throws Exception {
        return ordersDao.findAll();
    }
}
