package com.xd.eduOrder.service.impl;

import com.xd.commonutils.ordervo.CourseWebVoOrder;
import com.xd.commonutils.ordervo.UcenterMemberOrder;
import com.xd.eduOrder.client.EduClient;
import com.xd.eduOrder.client.UcenterClient;
import com.xd.eduOrder.domain.TOrder;
import com.xd.eduOrder.mapper.TOrderMapper;
import com.xd.eduOrder.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.eduOrder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-12-23
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {


    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;


    //生成订单的方法
    @Override
    public String createOrders(String courseId, String token) {
        //通过远程调用根据用户id获取用户信息
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(courseId);
        //通过远程调用根据课程id获取课程信息
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);
        //创建order对象,向order对象里面设置需要数据
        TOrder order = new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(userInfoOrder.getId());
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0); //支付状态,0微支付
        order.setPayType(1);//支付类型，微信1
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
