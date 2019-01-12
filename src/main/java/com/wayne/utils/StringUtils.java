package com.wayne.utils;

import javax.validation.constraints.NotNull;

public class StringUtils {

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
}
