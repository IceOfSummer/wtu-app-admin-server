package pers.xds.wtuapp.common.web.util;

/**
 * @author DeSen Xu
 * @date 2021-12-29 15:58
 */
public class StringUtils {
    private StringUtils() {}

    /**
     * 判断某个字符串是否为空
     * @param str 字符串
     * @return 返回ture表示为空
     */
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        return "".equals(str);
    }
}
