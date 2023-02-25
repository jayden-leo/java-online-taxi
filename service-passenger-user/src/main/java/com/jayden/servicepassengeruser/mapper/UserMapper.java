package com.jayden.servicepassengeruser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jayden.internalcommon.dto.PassengerUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<PassengerUser> {
}
