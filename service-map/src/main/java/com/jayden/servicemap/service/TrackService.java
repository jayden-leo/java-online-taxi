package com.jayden.servicemap.service;

import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.servicemap.remote.TrackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackService {

    @Autowired
    private TrackClient trackClient;

    public ResponseResult add(String tid){

        return trackClient.add(tid);
    }
}
