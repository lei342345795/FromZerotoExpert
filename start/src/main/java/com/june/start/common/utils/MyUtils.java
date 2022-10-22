package com.june.start.common.utils;

/**
 * @author Douzi
 */
public class MyUtils {
    /**
     * 判断用户名是否合法
     * @param trie 前缀树
     * @param s 用户名
     * @return true表示不合法，false表示合法
     */
    public static boolean isIllegal(Trie trie, String s) {
        return trie.ifContain(s);
    }
}

