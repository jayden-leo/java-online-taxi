package com.jayden.serviceorder.service;

import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.request.OrderRequest;
import com.jayden.serviceorder.entity.OrderInfo;
import com.jayden.serviceorder.mapper.OrderInfoMapper;
import org.springframework.beans.BeanUtils;
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


    public ResponseResult add(OrderRequest orderRequest){
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderRequest, orderInfo);
        orderInfoMapper.insert(orderInfo);
        return ResponseResult.success();
    }


}
