package com.baji.dom.controller;

import com.baji.dom.common.Result;
import com.baji.dom.common.ResultModel;
import com.baji.dom.entity.EportalUser;
import com.baji.dom.service.EportalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * eportal用户控制层
 *
 * @author dongmin
 * @date 2022/08/23
 */
@RestController
@RequestMapping("/user/info")
public class EportalUserController {

    @Autowired
    private EportalUserService eportalUserService;

    /**
     * 查询用户
     *
     * @return {@code Result<EportalUser>}
     */
    @PostMapping(value = "/queryUser")
    public ResultModel queryUser() {
        ResultModel resultModel = new ResultModel();
        List<EportalUser> list = eportalUserService.list();
        resultModel.setCode(0);
        resultModel.setMessage("获取用户列表成功");
        resultModel.setContent(list);
        return resultModel;
    }
}
