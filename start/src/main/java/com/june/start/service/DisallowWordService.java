package com.june.start.service;

import com.june.start.domain.DisallowWord;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Douzi
 */
@Service
public interface DisallowWordService {
    /**
     * 获取所有敏感词
     * @return 敏感词列表
     */
    List<DisallowWord> getAll();
}
