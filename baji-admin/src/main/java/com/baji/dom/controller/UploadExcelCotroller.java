package com.baji.dom.controller;

import cn.hutool.core.lang.Assert;
import com.baji.dom.common.Result;
import com.baji.dom.entity.SysAgent;
import com.baji.dom.service.UploadExcelService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 上传excel控制层
 *
 * @author dongmin
 * @date 2022/08/19
 */
@RestController
@RequestMapping("/admin/operateExcel")
public class UploadExcelCotroller {

    private static final Logger logger = Logger.getLogger(UploadExcelCotroller.class);

    @Resource
    private UploadExcelService uploadExcelService;

    /**
     * 上传excel
     *
     * @param request 请求
     * @return {@code Result<?>}
     */
    @PostMapping(value = "/uploadExcel")
    public Result<?> uploadExcel(MultipartRequest request) {
        Assert.notNull(request, "请上传文件");
        try {
            logger.info("开始解析文件！");
            List<SysAgent> agentList = uploadExcelService.uploadExcel(request);
            logger.info("解析文件成功！agentList:"+agentList);
            return Result.ok(agentList);
        } catch (Exception e) {
            logger.error("解析文件时出现异常", e);
            return Result.error("解析文件时出现异常");
        }
    }

    /**
     * 创建excel
     *
     * @param agentList 代理列表
     * @param response  响应
     * @return {@code Result<?>}
     */
    @PostMapping(value = "/createExcel")
    public Result<?> createExcel(@RequestBody List<SysAgent> agentList, HttpServletResponse response) {
        try {
            logger.info("开始下载文件！");
            Assert.notEmpty(agentList, "文件列表为空");
            uploadExcelService.createExcel(agentList, response);
            logger.info("下载文件成功！");
            return Result.ok();
        } catch (Exception e) {
            logger.error("下载文件时出现异常", e);
            return Result.error("下载文件时出现异常");
        }
    }
}
