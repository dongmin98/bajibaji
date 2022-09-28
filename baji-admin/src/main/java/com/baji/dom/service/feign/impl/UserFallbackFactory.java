package com.baji.dom.service.feign.impl;

import com.baji.dom.common.ResultModel;
import com.baji.dom.service.feign.UserClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFallbackFactory implements FallbackFactory<UserClient> {


    public UserClient create(Throwable throwable) {
        return new UserClient() {
            public ResultModel queryUser() {
                ResultModel resultModel = new ResultModel();
                resultModel.setCode(1);
                resultModel.setMessage("服务器运行异常");
                return resultModel;
            }
        };
    }
}
