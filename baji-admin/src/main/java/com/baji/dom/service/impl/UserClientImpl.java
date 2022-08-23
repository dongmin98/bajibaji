package com.baji.dom.service.impl;

import com.baji.dom.common.Result;
import com.baji.dom.common.ResultModel;
import com.baji.dom.entity.EportalUser;
import com.baji.dom.service.UserClient;
import org.springframework.stereotype.Component;

/**
 * 用户客户端实现
 *
 * @author dongmin
 * @date 2022/08/23
 */
@Component
public class UserClientImpl implements UserClient {

    public ResultModel queryUser() {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(1);
        resultModel.setMessage("服务器运行异常");
        return resultModel;
    }
}
