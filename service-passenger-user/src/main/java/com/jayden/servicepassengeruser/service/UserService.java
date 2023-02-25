package com.jayden.servicepassengeruser.service;

import com.jayden.internalcommon.constant.CommonStatusEnum;
import com.jayden.internalcommon.dto.PassengerUser;
import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.servicepassengeruser.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public ResponseResult loginOrRegisterUser(String passengerPhone){
        System.out.println("user service被调用，手机号："+passengerPhone);
        HashMap<String, Object> map = new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = userMapper.selectByMap(map);
        if (passengerUsers.size()==0){
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setPassengerName("11");
            passengerUser.setProfilePhoto("sadgashgd.com");
            passengerUser.setPassengerGender((byte) 1);
            passengerUser.setState((byte) 2);

            passengerUser.setGmtCreate(LocalDateTime.now());
            passengerUser.setGmtModified(LocalDateTime.now());

            userMapper.insert(passengerUser);
        }

        return ResponseResult.success();
    }

    /**
     * 根据手机号查询用户信息
     * @param passengerPhone
     * @return
     */
    public ResponseResult getUserByPhone(String passengerPhone){
        // 根据手机号查询用户信息
        Map<String,Object> map = new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = userMapper.selectByMap(map);
        if (passengerUsers.size() == 0){
            return ResponseResult.fail(CommonStatusEnum.USER_NOT_EXISTS.getCode(),CommonStatusEnum.USER_NOT_EXISTS.getMessage());
        } else {
            PassengerUser passengerUser = passengerUsers.get(0);
            return ResponseResult.success(passengerUser);
        }
    }
}
