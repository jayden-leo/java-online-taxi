package com.jayden.serviceorder.service;

import com.jayden.serviceorder.entity.OrderInfo;
import com.jayden.serviceorder.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jayden
 * @since 2023-03-10
 */
@Service
public class OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    public String testMapper(){
        OrderInfo order = new OrderInfo();
        order.setAddress("sdg");
        orderInfoMapper.insert(order);
        return "";
    }

}
