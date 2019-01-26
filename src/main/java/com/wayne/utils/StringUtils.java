package com.wayne.utils;

import javax.validation.constraints.NotNull;

public class StringUtils {

    private StringUtils(){};
    /**
     * 判断要搜索的字符串是否存在于目标字符串中
     * @param seq 目标字符串
     * @param searchSeq 待搜索的字符串
     * @return 如果存在则返回true;如果不存在，则返回false
     */
    public static boolean contains(@NotNull String seq, String searchSeq) {
        if (seq != null && searchSeq != null) {
            return seq.indexOf(searchSeq,0) >= 0;
        } else {
            return false;
        }
    }

    /**
     * 判断两个字符串是否相等
     * @param s1
     * @param s2
     * @return
     */
    public static boolean equals(String s1, String s2) {
        return s1 == null ? s2 == null : equals(s1.subSequence(0,s1.length()),s2.subSequence(0,s2.length()));
    }

    public static boolean equals(CharSequence cs1, CharSequence cs2) {
        return cs1 == null ? cs2 == null : cs1.equals(cs2);
    }

    public static boolean isNotEmpty(String s1){
        return null != s1 && s1.trim().length()>0;
    }
}
