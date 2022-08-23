package com.baji.dom.controller;

import com.baji.dom.common.ResultModel;
import com.baji.dom.service.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/info")
public class getUser {

    @Autowired
    private UserClient userClient;

    @PostMapping("/getUser")
    public ResultModel getUser() {
        ResultModel resultModel = userClient.queryUser();
        return resultModel;
    }

}
