package com.baji.dom.util;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CreateExcelUtils {
    public static void createExcel(List<T> list, List<String> nameList, Map<String, String> allNameAndFileName, Map<String, String> allName) {
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
        for (int i = 0; i < 50; i++) {
            XSSFRow row = sheet.createRow(i);
            for (int j = 0; j < nameList.size(); j++) {
                XSSFCell cell = row.createCell(j);
                if (i == 0) {
                    cell.setCellValue(nameList.get(j));
                    cell.setCellStyle(lockedStyle);
                    //设置单元格长度
                    sheet.setColumnWidth(j, nameList.get(j).getBytes().length * 256*3);
                } else {
                    row.setRowStyle(noLockedStyle);
                    cell.setCellStyle(noLockedStyle);
                    T t = list.get(i-1);
//                    allName
//                    cell.setCellValue();
                }
            }
        }
    }
}
