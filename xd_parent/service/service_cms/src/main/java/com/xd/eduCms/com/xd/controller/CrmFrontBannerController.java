package com.xd.eduCms.com.xd.controller;


import com.xd.commonutils.R;
import com.xd.eduCms.domain.CrmBanner;
import com.xd.eduCms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-12-07
 */
@RestController
@RequestMapping("/eduCms/banner")
@CrossOrigin
public class CrmFrontBannerController {


    @Autowired
    private CrmBannerService crmBannerService;

    //查询所有Banner
    @GetMapping("getAllBanner")
    public R getAllBanner(){
        List<CrmBanner> list = crmBannerService.selectAllBanner();
        return R.ok().data("list",list);
    }

}

