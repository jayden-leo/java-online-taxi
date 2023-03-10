package com.jayden.servicedriveruser.remote;


import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.response.TerminalResponse;
import com.jayden.internalcommon.response.TrackResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-map")
public interface ServiceMapClient {

    @PostMapping("/terminal/add")
    public ResponseResult<TerminalResponse> addTerminal(@RequestParam String name,@RequestParam String desc);

    @PostMapping("/track/add")
    public ResponseResult<TrackResponse> addTrack(@RequestParam String tid);

}
