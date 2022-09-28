package com.baji.dom.controller;

import com.baji.dom.common.ResultModel;
import com.baji.dom.entity.EportalUser;
import com.baji.dom.service.EportalUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    private static final Logger logger =  Logger.getLogger(EportalUserController.class);

    @Autowired
    private EportalUserService eportalUserService;

    /**
     * 查询用户
     *
     * @return {@code Result<EportalUser>}
     */
    @GetMapping(value = "/queryUser")
    public ResultModel queryUser() {
        ResultModel resultModel = new ResultModel();
        try {
            // 查询用户
            List<EportalUser> list = eportalUserService.list();
            resultModel.setCode(0);
            resultModel.setMessage("获取用户列表成功!");
            resultModel.setContent(list);
            logger.info("获取用户列表成功！");
        } catch (Exception e) {
            logger.error("获取用户列表失败，失败原因：" + e);
            resultModel.setCode(1);
            resultModel.setMessage("获取用户列表失败!");
        }

        return resultModel;
    }
}
