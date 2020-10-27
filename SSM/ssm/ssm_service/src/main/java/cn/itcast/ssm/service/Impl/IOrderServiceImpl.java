package cn.itcast.ssm.service.Impl;

import cn.itcast.ssm.dao.IOrdersDao;
import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.service.IOrderService;
import com.github.pagehelper.PageHelper;
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
    public List<Orders> findAll(int page,int size) throws Exception {
        System.out.println("jinlaile");
        PageHelper.startPage(page,size);
        System.out.println("准备开始查");
        List<Orders> all = ordersDao.findAll();
        for (Orders orders : all) {
            System.out.println(orders);
        }

        return all;
    }

    @Override
    public Orders findById(String id) {
        return ordersDao.findById(id);
    }
}
