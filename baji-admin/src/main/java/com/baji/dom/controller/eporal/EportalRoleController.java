package com.baji.dom.controller.eporal;

import com.baji.dom.common.ResultModel;
import com.baji.dom.entity.EportalRole;
import com.baji.dom.service.epotal.EportalRoleResourceService;
import com.baji.dom.service.epotal.EportalRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * 后管角色控制层
 *
 * @author dongmin
 * @date 2022/09/28
 */
@RestController
@RequestMapping("/eportal/role")
public class EportalRoleController {

    private static Logger logger = Logger.getLogger(EportalRoleController.class.getName());

    @Autowired
    private EportalRoleService eportalRoleService;
    @Autowired
    private EportalRoleResourceService eportalRoleResourceService;


    /**
     * 得到管理员的角色
     *
     * @return {@code ResultModel}
     */
    @PostMapping("/getAllRole")
    private ResultModel getAllRole(@RequestBody(required=false) EportalRole eportalRole) {
        logger.info("进入角色查询方法，入参：" + eportalRole);
        ResultModel resultModel = new ResultModel();
        try {
            // 条件查询角色信息
            List<EportalRole> roleList = eportalRoleService.queryList(eportalRole);
            resultModel.setCode(0);
            resultModel.setMessage("获取角色列表成功！");
            resultModel.setContent(roleList);
            logger.info("获取角色列表成功，角色列表为：" + roleList);
        } catch (Exception e) {
            resultModel.setCode(0);
            resultModel.setMessage("获取角色列表失败！");
            logger.info("获取角色列表失败，错误原因为：" + e);
        }
        return resultModel;
    }


}
