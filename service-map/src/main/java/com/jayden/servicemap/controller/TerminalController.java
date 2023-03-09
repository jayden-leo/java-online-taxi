package com.jayden.servicemap.controller;

import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.response.TerminalResponse;
import com.jayden.servicemap.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/terminal")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    @PostMapping("/add")
    public ResponseResult<TerminalResponse> add(String name){
        return terminalService.add(name);
    }
}
