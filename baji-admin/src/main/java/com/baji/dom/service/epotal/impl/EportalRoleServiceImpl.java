package com.baji.dom.service.epotal.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baji.dom.entity.EportalRole;
import com.baji.dom.service.epotal.EportalRoleService;
import com.baji.dom.mapper.EportalRoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * eportal角色业务层实现
 *
 * @author dongmin
 * @date 2022/09/28
 */
@Service
public class EportalRoleServiceImpl extends ServiceImpl<EportalRoleMapper, EportalRole>
    implements EportalRoleService{

    /**
     * 查询列表
     *
     * @param eportalRole eportal作用
     * @return {@code List<EportalRole>}
     */
    public List<EportalRole> queryList(EportalRole eportalRole) {
        LambdaQueryWrapper<EportalRole> queryWrapper = Wrappers.lambdaQuery(eportalRole);
        return this.list(queryWrapper);
    }
}




