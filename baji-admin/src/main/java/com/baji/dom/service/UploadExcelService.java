package com.baji.dom.service;

import com.baji.dom.entity.SysAgent;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 上传费excel业务层
 *
 * @author dongmin
 * @date 2022/08/18
 */
public interface UploadExcelService {

    /**
     * 上传excel
     *
     * @param request 请求
     * @return {@code List<SysAgent>}
     */
    List<SysAgent> uploadExcel(MultipartRequest request);

    /**
     * 创建excel
     *
     * @param agentList 代理列表
     * @param response  响应
     */
    void createExcel(List<SysAgent> agentList, HttpServletResponse response);
}
