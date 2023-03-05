package com.jayden.servicemap.controller;

import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.servicemap.service.ServiceMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务管理控制器
 */
@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceMapService serviceMapService;

    /**
     * 创建服务
     * @param name
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(String name){
        return serviceMapService.add(name);
    }

}
