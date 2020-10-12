package cn.itcast.Service.Impl;

import cn.itcast.Service.ItemsService;
import cn.itcast.dao.Itemsdao;
import cn.itcast.domain.Items2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private Itemsdao idao;

    public Items2 findById(Integer id) {
        return idao.findById(id);
    }
}
