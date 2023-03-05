package com.jayden.servicemap.service;

import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.servicemap.remote.TerminalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerminalService {

    @Autowired
    private TerminalClient terminalClient;

    public ResponseResult add(String name){
        return terminalClient.add(name);
    }
}
