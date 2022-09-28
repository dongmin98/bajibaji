package com.baji.dom.service.epotal;

import com.baji.dom.entity.EportalRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * eportal角色业务层
 *
 * @author dongmin
 * @date 2022/09/28
 */
public interface EportalRoleService extends IService<EportalRole> {

    /**
     * 查询列表
     *
     * @param eportalRole eportal作用
     * @return {@code List<EportalRole>}
     */
    List<EportalRole> queryList(EportalRole eportalRole);
}
