package com.jayden.apiboss.service;

import com.jayden.apiboss.remote.ServiceDriverUserClient;
import com.jayden.internalcommon.dto.DriverCarBindingRelationship;
import com.jayden.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jayden
 * @since 2023-02-26
 */
@Service
public class DriverCarBindingRelationshipService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship) {
        return serviceDriverUserClient.bind(driverCarBindingRelationship);
    }

    public ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship) {
        return serviceDriverUserClient.unbind(driverCarBindingRelationship);
    }
}
