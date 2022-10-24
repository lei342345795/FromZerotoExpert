package com.june.start.common.utils;

import com.june.start.domain.DisallowWord;
import com.june.start.service.DisallowWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Douzi
 */
@Component
public class MyUtils {
    @Autowired
    DisallowWordService disallowWordService;
    private static Trie trie = new Trie();
    @PostConstruct
    public void init() {
        List<DisallowWord> disallowWords = disallowWordService.getAll();
        System.out.println("get");
        for (DisallowWord disallowWord : disallowWords) {
            trie.put(disallowWord.getValue());
        }
    }
    /**
     * 判断用户名是否合法
     * @param s 用户名
     * @return true表示不合法，false表示合法
     */
    public static boolean isIllegal(String s) {
        return trie.ifContain(s);
    }
}

