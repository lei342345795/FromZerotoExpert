package com.june.start.common.utils;

import com.june.start.domain.DisallowWord;
import com.june.start.service.DisallowWordService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.Base64;
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
     *
     * @param s 用户名
     * @return true表示不合法，false表示合法
     */
    public static boolean isIllegal(String s) {
        return trie.ifContain(s);
    }

    /**
     * 生成加密后的密码
     *
     * @param password 原密码
     * @return 加密后的密码
     */
    public static String getBcrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * 检查密码是否正确
     *
     * @param password 此次输入的密码
     * @param bcrypt   正确的密码
     * @return 布尔值，true表示密码正确，反之表示密码错误
     */
    public static boolean checkBcrypt(String password, String bcrypt) {
        return BCrypt.checkpw(password, bcrypt);
    }

    public static String getRandom() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[12];
        secureRandom.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

}


