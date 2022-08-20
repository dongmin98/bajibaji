package com.baji.dom.service.impl;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baji.dom.entity.SysAgent;
import com.baji.dom.excelEnum.AGENT_USER_EXCEL_ENUM;
import com.baji.dom.service.UploadExcelService;
import com.baji.dom.util.DataHandleUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 上传excel业务层实现
 *
 * @author dongmin
 * @date 2022/08/19
 */
@Service
public class UploadExcelServiceImpl implements UploadExcelService {

    private static final Logger logger = Logger.getLogger(UploadExcelServiceImpl.class);

    /**
     * 上传excel
     *
     * @param request 请求
     * @return {@code List<SysAgent>}
     */
    public List<SysAgent> uploadExcel(MultipartRequest request) {
        HashMap<String, String> head = new HashMap<String, String>();
        List<SysAgent> agentList = new ArrayList<SysAgent>();
        List<SysAgent> sysAgents = new ArrayList<SysAgent>();
        InputStream file = null;
        try {
            // 获取文件
            MultipartFile multipartFile = request.getFile("file");
            // 文件内容
            file = multipartFile.getInputStream();
            // 文件名
            String fileName = multipartFile.getOriginalFilename().toUpperCase();
            // 文件后缀
            String extString = fileName.substring(fileName.lastIndexOf("."));
            // 校验文件后缀
            if (!".XLS".equals(extString) && !".XLSX".equals(extString)) {
                throw new RuntimeException("请选择指定文件");
            }
            // 获取文件头
            for (AGENT_USER_EXCEL_ENUM value : AGENT_USER_EXCEL_ENUM.values()) {
                head.put(value.getName(), value.getFileName());
            }
            ExcelReader reader = ExcelUtil.getReader(file);
            reader.setHeaderAlias(head);
            // 读取文件
            agentList = reader.readAll(SysAgent.class);
            if (agentList == null || agentList.size() == 0) {
                throw new RuntimeException("excel中不存在业务员");
            }
            int i = 2;
            for (SysAgent sysAgent : agentList) {
                DataHandleUtils.checkString(sysAgent.getAgentCode(),"EXCEL第"+i+"行"+AGENT_USER_EXCEL_ENUM.AGENT_USER_CODE.getName());
                DataHandleUtils.checkString(sysAgent.getAgentName(),"EXCEL第"+i+"行"+AGENT_USER_EXCEL_ENUM.AGENT_USER_NAME.getName());
                DataHandleUtils.checkString(sysAgent.getManageCode(),"EXCEL第"+i+"行"+AGENT_USER_EXCEL_ENUM.AGENT_USER_MANAGE_CODE.getName());
                DataHandleUtils.checkString(sysAgent.getManageName(),"EXCEL第"+i+"行"+AGENT_USER_EXCEL_ENUM.AGENT_USER_MANAGE_NAME.getName());
                DataHandleUtils.checkMobile(sysAgent.getMobile(), "EXCEL第"+i+"行"+AGENT_USER_EXCEL_ENUM.AGENT_USER_MOBILE.getName());
                DataHandleUtils.checkDateFormat(sysAgent.getBirthday(), "", "EXCEL第"+i+"行"+AGENT_USER_EXCEL_ENUM.AGENT_USER_BIRTHDAY.getName());
                DataHandleUtils.checkInteger(sysAgent.getAgentType(), "EXCEL第"+i+"行"+AGENT_USER_EXCEL_ENUM.AGENT_USER_TYPE.getName());
                DataHandleUtils.checkInteger(sysAgent.getAgentStatus(), "EXCEL第"+i+"行"+AGENT_USER_EXCEL_ENUM.AGENT_USER_STATUS.getName());
                if (sysAgents.contains(sysAgent.getAgentCode())) {
                    throw new RuntimeException(sysAgent.getAgentCode() + "代理人代码存在重复数据");
                }
                sysAgents.add(sysAgent);
                i++;
            }
        } catch (Exception e) {
            logger.error("解析excel文件出现异常", e);
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                logger.error("关流失败", e);
            }
        }
        return sysAgents;
    }

    /**
     * 创建excel
     *
     * @param agentList 代理列表
     * @param response  响应
     */
    public void createExcel(List<SysAgent> agentList, HttpServletResponse response) {
        //创建工作簿
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("业务员");
        //设置全表锁定,并能自由拉动列宽
        sheet.lockFormatColumns(false);
        sheet.protectSheet("XXX");
        CTSheetProtection sheetProtection = sheet.getCTWorksheet().getSheetProtection();
        //给与新增一行权限
        sheetProtection.setInsertRows(false);
        //给与删除一行权限
        sheetProtection.setDeleteRows(false);
        //设置表头样式
        XSSFCellStyle lockedStyle = wb.createCellStyle();
        lockedStyle.setAlignment(HorizontalAlignment.CENTER);
        XSSFCellStyle noLockedStyle = wb.createCellStyle();
        noLockedStyle.setLocked(false);
        noLockedStyle.setAlignment(HorizontalAlignment.CENTER);

        AGENT_USER_EXCEL_ENUM[] excelValues = AGENT_USER_EXCEL_ENUM.values();
        for (int i = 0; i < 50; i++) {
            XSSFRow row = sheet.createRow(i);
            for (int j = 0; j < excelValues.length; j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(excelValues[j].getName());
                if(i == 0){
                    cell.setCellStyle(lockedStyle);
                    //设置单元格长度
                    sheet.setColumnWidth(j, excelValues[j].getName().getBytes().length * 256*3);
                } else {
                    row.setRowStyle(noLockedStyle);
                    cell.setCellStyle(noLockedStyle);
                    if (i < agentList.size() + 1) {
                        SysAgent sysAgent = agentList.get(i-1);

                        Map<String, String> allName = AGENT_USER_EXCEL_ENUM.getAllName(sysAgent);
                        cell.setCellValue(allName.get(excelValues[j].getName()));
                    }
                }
            }
            ServletOutputStream outputStream = null;
            try {
                response.setHeader("Content-Disposition", "attachment;fileName='业务人员配置.xlsx'");// 设置文件名
                outputStream = response.getOutputStream();
                wb.write(outputStream);
            } catch (IOException e) {
                logger.error("写出excel异常", e);
            } finally {
                try {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (IOException e) {
                    logger.error("关流失败", e);
                }
            }
        }
    }
}
