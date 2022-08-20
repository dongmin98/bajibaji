package com.baji.dom.util;

import cn.hutool.core.util.NumberUtil;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据处理实效
 *
 * @author dongmin
 * @date 2022/08/18
 */
public class DataHandleUtils {


    /**
     * 判断某个字符串是否为大于的数字类型,并不能大于max
     *
     * @return
     */
    public static boolean checkBigDecimal(String num, BigDecimal max, String msg) {
        //判断某个值是否为空
        if (StringUtils.isEmpty(num)) {
            throw new RuntimeException(msg + "不能为空");
        }
        //判断excel公式是否生效问题
        if("#VALUE!".equals(num)){
            throw new RuntimeException("不要上传相同的数据,修改后再上传");
        }
        if (!NumberUtil.isNumber(num)) {
            throw new RuntimeException(msg + "非数字类型");
        }

        if (max != null) {
            BigDecimal number = NumberUtil.toBigDecimal(num);
            if (number.compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException(msg + "不能小于0");
            }
            if (number.compareTo(max) > 0) {
                throw new RuntimeException(msg + "不能大于" + max);
            }
        }
        return true;
    }


    /**
     * 判断某个字符串是否为数字类型,并大于0
     *
     * @return
     */
    public static boolean checkBigDecimalForZero(String num, BigDecimal min, String msg) {
        //判断某个值是否为空
        if (StringUtils.isEmpty(num)) {
            throw new RuntimeException(msg + "不能为空");
        }
        if (!NumberUtil.isNumber(num)) {
            throw new RuntimeException(msg + "非数字类型");
        }

        if (min != null) {
            BigDecimal number = NumberUtil.toBigDecimal(num);
            if (number.compareTo(min) < 0) {
                throw new RuntimeException(msg + "不能小于" + min);
            }
        }
        return true;
    }


    /**
     * 判断某个字符串是否为小数类型(0-1)
     *
     * @return
     */
    public static boolean checkDecimal(String num, String msg) {
        //判断某个值是否为空
        if (StringUtils.isEmpty(num)) {
            throw new RuntimeException(msg + "不能为空");
        }
        if (!NumberUtil.isNumber(num)) {
            throw new RuntimeException(msg + "非数字类型");
        }


        BigDecimal number = NumberUtil.toBigDecimal(num);
        if (number.compareTo(BigDecimal.ONE) > 0) {
            throw new RuntimeException(msg + "不能大于1");
        }
        if (number.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException(msg + "不能小于0");
        }

        return true;
    }


    /**
     * 判断某个字符串是否为空
     *
     * @return
     */
    public static boolean checkString(String str, String msg) {
        //判断某个值是否为空
        if (StringUtils.isEmpty(str)) {
            throw new RuntimeException(msg + "不能为空");
        }
        return true;
    }

    /**
     * 判断某个字符串是否为空
     *
     * @return
     */
    public static boolean checkInteger(Integer i, String msg) {
        //判断某个值是否为空
        if (i == null) {
            throw new RuntimeException(msg + "不能为空");
        }
        return true;
    }


    /**
     * 字符串去掉尾数0
     *
     * @param str
     * @return
     */
    public static String removeZero(String str) {
        if (!StringUtils.isEmpty(str)) {
            return str.replaceAll("(0)+$", "");
        }
        return str;
    }

    /**
     * 字符串去掉尾数0
     *
     * @param dateStr 时间字符串
     * @param format  字符串格式,空默认yyyy-MM-dd HH:mm:ss
     * @param dateStr
     * @return
     */
    public static boolean checkDateFormat(String dateStr, String format, String msg) throws ParseException {
        checkString(dateStr, msg);
        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format); //转换日期格式
            simpleDateFormat.parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException(msg + "时间格式不对");
        }
        return true;
    }


    /**
     * 校验身份证号
     *
     * @return
     */
    public static boolean checkNo(String str, String msg) throws ParseException {
        if (StringUtils.isEmpty(str)) {
            throw new RuntimeException(msg + "不能为空");
        }
        try {
            if (!IdCardUtils.checkIdCard(str)) {
                throw new RuntimeException(msg + "格式异常");
            }
        }catch (Exception e){
            throw new RuntimeException(str+ "身份证校验出现异常");
        }
        return true;
    }


    /**
     * 校验手机号
     * @return
     */
    public static boolean checkMobile(String str, String msg) throws ParseException {
        if (StringUtils.isEmpty(str)) {
            throw new RuntimeException(msg + "不能为空");
        }
        //正则校验
        String regex = "^(1[3-9]\\d{9}$)";
        Pattern verifyCode = Pattern.compile(regex);
        Matcher matcher = verifyCode.matcher(str);
        if (str.length()!=11 || !matcher.matches()){
            throw new RuntimeException(msg + "格式异常");
        }
        return true;
    }


}
