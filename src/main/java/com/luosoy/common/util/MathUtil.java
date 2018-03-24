package com.luosoy.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class MathUtil {

    private MathUtil() {

    }

    /**  BigDecimal两位小数精度*/
    public static final int SCALE_TWO = 2;

    /** 格式化金额，千位以逗号分隔，保留2位小数*/
    private static java.text.DecimalFormat dff2 = new java.text.DecimalFormat("#,##0.00");

    /**  格式化金额保留4位小数*/
    private static java.text.DecimalFormat df4 = new java.text.DecimalFormat("0.0000");

    /**  格式化金额保留6位小数*/
    private static java.text.DecimalFormat df6 = new java.text.DecimalFormat("0.000000");

    private static final String UNIT_DIGITAL[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };

    private static final String UNIT_MONEY[] = { "万", "仟", "佰", "拾", "亿", "仟", "佰", "拾", "万", "仟", "佰", "拾", "", "圆",
            "角", "分" };

    private static final int MAX = UNIT_MONEY.length;

    /**
     * @Title: getDoubleByBigDecimal
     * @Description:  根据BigDecimal类型数字获取Double类型
     * @param
     * @return double
     */
    public static double getDoubleByBigDecimal(BigDecimal bigDecimal) {
        if (bigDecimal != null) {
            return bigDecimal.doubleValue();
        }
        return 0;
    }

    /**
     * @Title: getLongByBigDecimal
     * @Description:  根据BigDecimal类型数字获取long类型
     * @param
     * @return long
     */
    public static long getLongByBigDecimal(BigDecimal bigDecimal) {
        if (bigDecimal != null) {
            return bigDecimal.longValue();
        }
        return 0;
    }

    /**
    * @Title: bigDecimalFtwoHup
    * @Description: 向最接近数字方向四舍五入模式，保留两位小数
    * @param
    * @return BigDecimal    返回类型
    * @throws
    */
    public static BigDecimal bigDecimalFtwoHup(BigDecimal bigDecimal) {
        return bigDecimal.setScale(SCALE_TWO, RoundingMode.HALF_UP);
    }

    /**
    * @Title: bigDecimalFtwoHdown
    * @Description: 截取模式，保留两位小数
    * @param
    * @return BigDecimal    返回类型
    * @throws
    */
    public static BigDecimal bigDecimalFtwoDown(BigDecimal bigDecimal) {
        return bigDecimal.setScale(SCALE_TWO, RoundingMode.DOWN);
    }

    /**
    * @Title: formatMToString2
    * @Description:  格式化金额，千位以逗号分隔，保留2位小数
    * @param   je 金额
    * @return String    返回类型
    * @throws
    */
    public static String formatMToString2(BigDecimal je) {
        return dff2.format(je);
    }

    /**
    * @Title: formatToString4
    * @Description: 格式化金额保留4位小数
    * @param    je 金额
    * @return String    返回类型
    * @throws
    */
    public static String formatToString4(BigDecimal je) {
        return df4.format(je);
    }

    /**
    * @Title: formatToString6
    * @Description: 格式化金额保留6位小数
    * @param   je 金额
    * @return String    返回类型
    * @throws
    */
    public static String formatToString6(BigDecimal je) {
        return df6.format(je);
    }

    /**
     * @Title: toBigDecimal
     * @Description: double转化为BigDecimal<br>
     * @param value
     * @return BigDecimal
     */
    public static BigDecimal toBigDecimal(double value) {
        return new BigDecimal(String.valueOf(value));
    }

    /**
     * @Title: nvl
     * @Description: 返回非null的BigDecimal
     * @param bd BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal nvl(BigDecimal bd) {
        if (null == bd) {
            return new BigDecimal("0");
        }
        return bd;
    }

    /**
     * @Title: nvl
     * @Description: 返回非null的Integer
     * @param integer Integer
     * @return Integer
     */
    public static Integer nvl(Integer integer) {
        if (null == integer) {
            return 0;
        }
        return integer;
    }

    /**
     * @Description: 返回非null的Double
     * @author  zlf
     * @date  2017年12月16日 下午2:12:29
     * @param d Double
     * @return Double
     */
    public static Double nvl(Double d) {
        if (null == d) {
            return new Double("0");
        }
        return d;
    }

    /**
     * @Title: add
     * @Description: 计算合计
     * @param bd1 BigDecimal
     * @param bd2 BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal add(BigDecimal bd1, BigDecimal bd2) {
        return nvl(bd1).add(nvl(bd2));
    }

    /**
     * @Title: subtract
     * @Description: 减法
     * @param bd1 BigDecimal
     * @param bd2 BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal subtract(BigDecimal bd1, BigDecimal bd2) {
        return nvl(bd1).subtract(nvl(bd2));
    }

    /**
     * @Title: formatBDToString2
     * @Description:  格式化金额，千位以逗号分隔，保留2位小数,添加了非空校验
     * @param   je 金额
     * @return String    返回类型
     * @throws
     */
    public static String formatBDToString2(BigDecimal je) {
        return dff2.format(nvl(je));
    }

    /**
     * @Title: decToChinese
     * @Description: 将数字转换成为货币表达式，例如：10000.40 --> 壹万圆肆角整
     * @param
     * @return String    返回类型
     * @throws
     */
    public static String decToChinese(double forDecValue) {
        if (forDecValue < 0 || forDecValue > 9999999999999.99d) {
            return "";
        }
        String val = new BigDecimal(forDecValue).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        String intval = val.substring(0, val.indexOf("."));
        String fraction = val.substring(val.indexOf(".") + 1).substring(0, 2);
        val = (new StringBuffer(intval)).append(".").append(fraction).toString();
        StringBuffer out = new StringBuffer("");
        int len = val.length();
        for (int i = 0; i < len; i++) {
            int moneyPos = (MAX - len) + i;
            if ((val.charAt(i) == '0' && i < len - 1 && val.charAt(i + 1) == '0')
                    || (val.charAt(i) == '0' && "万".equals(UNIT_MONEY[(MAX - len) + i]))
                    || (val.charAt(i) == '0' && "亿".equals(UNIT_MONEY[(MAX - len) + i]))) {
                // 如果为0，那么还要判断是否需打印刻度(亿、万）
                if ("亿".equals(UNIT_MONEY[moneyPos])) {
                    out.append("亿");
                } else {
                    if (out.charAt(out.length() - 1) != '亿' && "万".equals(UNIT_MONEY[(MAX - len) + i])) { // 2003-11-26
                        out.append("万");
                    }
                }
                continue; // 合并零
            }
            if (val.charAt(i) == '0' && i < len - 1 && val.charAt(i + 1) == '.') {
                if (i == 0) {
                    out.append("零");
                }
                continue;
            }
            if (val.charAt(i) == '0' && i == len - 1) {
                continue;
            }
            if (val.charAt(i) != '.') {
                out.append(UNIT_DIGITAL[Integer.parseInt(val.substring(i, i + 1))]);
            }
            if (val.charAt(i) != '0' || (val.charAt(i) == '0' && i > 0 && val.charAt(i - 1) == '.')) {
                out.append(UNIT_MONEY[(MAX - len) + i]);
            }
            if (out.toString().indexOf("零角") != -1) {
                out = out.replace(0, out.length(), out.toString().substring(0, out.toString().indexOf("角")));
            }
        }
        if (!(out.toString().indexOf("分") >= 0)) {
            out.append("整");
        }
        return out.toString();
    }
}
