package com.jayden.servicedriveruser.controller;

import com.jayden.internalcommon.constant.DriverCarConstants;
import com.jayden.internalcommon.dto.DriverUser;
import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.response.DriverUserExistsResponse;
import com.jayden.servicedriveruser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private DriverUserService driverUserService;

    /**
     * 增加司机
     *
     * @param driverUser
     * @return
     */
    @PostMapping("/user")
    public ResponseResult addUser(@RequestBody DriverUser driverUser) {
        return driverUserService.addDriverUser(driverUser);
    }

    /**
     * 修改司机
     *
     * @param driverUser
     * @return
     */
    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser) {
        return driverUserService.updateDriverUser(driverUser);
    }

    /**
     * 查询 司机
     * 如果需要按照司机的多个条件做查询，那么此处用 /user
     * @return
     */
    @GetMapping("/check-driver/{driverPhone}")
    public ResponseResult<DriverUserExistsResponse> getUser(@PathVariable("driverPhone") String driverPhone){

        ResponseResult<DriverUser> driverUserByPhone = driverUserService.getDriverUserByPhone(driverPhone);
        DriverUser driverUserDb = driverUserByPhone.getData();

        DriverUserExistsResponse response = new DriverUserExistsResponse();

        int ifExists = DriverCarConstants.DRIVER_EXISTS;
        if (driverUserDb == null){
            ifExists = DriverCarConstants.DRIVER_NOT_EXISTS;
            response.setDriverPhone(driverPhone);
            response.setIfExists(ifExists);
        }else {
            response.setDriverPhone(driverUserDb.getDriverPhone());
            response.setIfExists(ifExists);
        }

        return ResponseResult.success(response);
    }
}
