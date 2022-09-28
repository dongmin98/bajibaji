package com.baji.dom.service.feign;

import com.baji.dom.common.ResultModel;
import com.baji.dom.service.feign.impl.UserClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 用户客户端
 *
 * @author dongmin
 * @date 2022/08/23
 */
@FeignClient(value = "bajiUser", fallback = UserClientImpl.class, path = "/baji-user")
//@FeignClient(value = "bajiUser", fallbackFactory = UserFallbackFactory.class)
public interface UserClient {

    /**
     * 查询用户
     *
     * @return {@code Result<EportalUser>}
     */
    @GetMapping("/user/info/queryUser")
    public ResultModel queryUser();

}
