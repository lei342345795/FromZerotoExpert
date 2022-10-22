package com.june.start;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Set;

@SpringBootTest
class StartApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void trie() {
        HashMap<Object, Object> map = new HashMap<>();
        Set<Object> objects = map.keySet();
        System.out.println(objects);
    }

}
