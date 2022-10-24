package com.june.start.service.serviceImpl;

import com.june.start.domain.DisallowWord;
import com.june.start.mapper.DisallowWordMapper;
import com.june.start.service.DisallowWordService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Douzi
 */
@Service
public class DisallowWordServiceImpl implements DisallowWordService {
    @Autowired
    DisallowWordMapper disallowWordMapper;
    @Override
    public List<DisallowWord> getAll() {
      return disallowWordMapper.getAll();
    }
}
