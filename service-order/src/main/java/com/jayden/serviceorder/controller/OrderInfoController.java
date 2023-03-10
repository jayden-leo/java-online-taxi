package com.jayden.serviceorder.controller;

import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.request.OrderRequest;
import com.jayden.serviceorder.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest){
        System.out.println(orderRequest);

        return orderInfoService.add(orderRequest);
    }

}
