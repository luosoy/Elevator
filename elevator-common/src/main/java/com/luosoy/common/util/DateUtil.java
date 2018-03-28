package com.luosoy.common.util;

import com.luosoy.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
public class DateUtil {

    /**
     * 日期常量后缀说明： ZHX：中划线："-" XHX: 下划线："_" ZXX: 正划线："/" ZW: 中文：年、月、日
     */

    /** 日期格式，年份 */
    public static final String DF_YYYY = "yyyy";

    /** 日期格式，月份(M) */
    public static final String DF_M = "M";
    /** 日期格式，月份(MM) */
    public static final String DF_MM = "MM";
    /** 日期格式，月份(M月) */
    public static final String DF_M_ZW = "M月";
    /** 日期格式，月份(MM月) */
    public static final String DF_MM_ZW = "MM月";

    /** yyyy-MM */
    public static final String DF_YYYYMM_ZHX = "yyyy-MM";
    /** yyyy_MM */
    public static final String DF_YYYYMM_XHX = "yyyy_MM";
    /** yyyy/MM */
    public static final String DF_YYYYMM_ZXX = "yyyy/MM";
    /** yyyy年M月 */
    public static final String DF_YYYYM_ZW = "yyyy年M月";
    /** yyyy年MM月 */
    public static final String DF_YYYYMM_ZW = "yyyy年MM月";
    /** yyyyMM */
    public static final String DF_YYYYMM = "yyyyMM";

    /** yyyy-MM-dd */
    public static final String DF_YYYYMMDD_ZHX = "yyyy-MM-dd";
    /** yyyy_MM_dd */
    public static final String DF_YYYYMMDD_XHX = "yyyy_MM_dd";
    /** yyyy/MM/dd */
    public static final String DF_YYYYMMDD_ZXX = "yyyy/MM/dd";
    /** yyyy年MM月dd日 */
    public static final String DF_YYYYMMDD_ZW = "yyyy年MM月dd日";
    /** yyyy年M月d日 */
    public static final String DF_YYYYMD_ZW = "yyyy年M月d日";
    /** yyyyMMdd */
    public static final String DF_YYYYMMDD = "yyyyMMdd";

    /** yyyy-MM-dd HH:mm:ss */
    public static final String DF_YYYYMMDD_HHMMSS_ZHX = "yyyy-MM-dd HH:mm:ss";
    /** yyyy_MM_dd HH:mm:ss */
    public static final String DF_YYYYMMDD_HHMMSS_XHX = "yyyy_MM_dd HH:mm:ss";
    /** yyyy/MM/dd HH:mm:ss */
    public static final String DF_YYYYMMDD_HHMMSS_ZXX = "yyyy/MM/dd HH:mm:ss";
    /** yyyyMMdd HH:mm:ss */
    public static final String DF_YYYYMMDD_HHMMSS = "yyyyMMdd HH:mm:ss";
    /** yyyy-MM-dd HH:mm */
    public static final String DF_YYYYMMDD_HHMM_ZHX = "yyyy-MM-dd HH:mm";

    /** yyyyMMddHHmmss */
    public static final String DF_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    /** yyyyMMddHHmmssSSS */
    public static final String DF_YYYYMMDDHHMMSSMS = "yyyyMMddHHmmssSSS";

    /** 1年12个月份 */
    public static final int MONTH_NUM_OF_YEAR = 12;

    private DateUtil() {
    }

    /***
     * @Title: toDate @Description: 转换为日期 @param fmt 格式 @param dateStr
     * 日期串 @return Date @throws
     */
    public static Date toDate(String fmt, String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            String errMsg = String.format("错误的日期参数：%1$s，不满足[%2$s]格式要求", dateStr, fmt);
            log.debug(errMsg, e);
            throw new BusinessException("错误的日期参数", e);
        }
    }

    /**
     * @Title: getSysDate @Description: 获取当前系统日期 @param format 格式 @return
     *         String @throws
     */
    public static String getSysDate(String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    /***
     * @Description: 获取只有年月日值，时分秒为空的值；
     * @param
     * @return java.util.Date
     */
    public static Date getSysDate() {
        String dateStr = getSysDate(DateUtil.DF_YYYYMMDD_ZHX);
        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DF_YYYYMMDD_ZHX);
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            log.debug("获取系统时间错误", e);
            throw new BusinessException("获取系统时间错误", e);
        }
        return date;
    }

    /**
     * @Description: 根据传入的年份，返回那年的第一天
     * @param year
     * @return java.lang.String
     */
    public static String getFirstDayOfYear(String year) {
        return year + "-01-01";
    }

    /**
     * @Description: 根据传入的年份，返回那年的最后一天
     * @param year
     * @return java.lang.String
     */
    public static String getLastDayOfYear(String year) {
        return year + "-12-31";
    }

    /**
     * 格式化日期
     *
     * @param date
     *            时间
     * @param formatType
     *            日期格式类型,例如:"yyyy-MM-dd" "yyyy-MM-dd HH:mm"
     * @return String 格式转换成功后的字符串
     */
    public static String doDateFormat(Date date, String formatType) {
        DateFormat df = new SimpleDateFormat(formatType);
        return df.format(date);
    }
}
