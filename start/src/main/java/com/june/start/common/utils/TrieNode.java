package com.june.start.common.utils;

import java.util.HashMap;

/**
 * @author Douzi
 */
public class TrieNode {
        private char aChar;
        private HashMap<Character, com.june.start.common.utils.TrieNode> map;

    public TrieNode() {
        map = new HashMap<>();
    }

    public TrieNode(char aChar) {
            this.aChar = aChar;
            map = new HashMap<>();

    }

        public HashMap<Character, com.june.start.common.utils.TrieNode> getMap() {
            return map;
        }
}
