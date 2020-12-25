package com.xd.eduCms.com.xd.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.commonutils.R;
import com.xd.eduCms.domain.CrmBanner;
import com.xd.eduCms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class CrmAdminBannerController {

    @Autowired
    private CrmBannerService crmBannerService;

    //1 分页查询banner
    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page,
                        @PathVariable long limit){
        Page<CrmBanner> pageBanner  = new Page<>(page,limit);
        crmBannerService.page(pageBanner,null);
        List<CrmBanner> records = pageBanner.getRecords();
        long total = pageBanner.getTotal();

        Map map  = new HashMap();
        map.put("total",total);
        map.put("data",records);
        return R.ok().data(map);
    }

    //添加banner
    @PostMapping("addBanner")
    public  R addBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.save(crmBanner);
        return R.ok();
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public R updateById(@RequestBody CrmBanner banner) {
        crmBannerService.updateById(banner);
        return R.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        crmBannerService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = crmBannerService.getById(id);
        return R.ok().data("item", banner);
    }

}

