package com.jayden.servicepassengeruser.service;

import com.jayden.internalcommon.dto.PassengerUser;
import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.servicepassengeruser.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

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
}
