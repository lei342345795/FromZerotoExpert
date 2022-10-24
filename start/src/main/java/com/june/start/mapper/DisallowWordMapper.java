package com.june.start.mapper;

import com.june.start.domain.DisallowWord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Douzi
 */
@Mapper
public interface DisallowWordMapper {
    /**
     * 获取数据库中所有敏感词
     * @return 敏感词列表
     */
    @Select("select * from disallow_word")
    List<DisallowWord> getAll();
}
