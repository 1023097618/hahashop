package com.mall.dao;

import com.mall.entity.History;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HistoryDao {
    Integer addHistory(History history);
    Integer deleteHistory(Integer historyId);
    List<History> getHistory(Integer pageSize, Integer pageNum);
    Integer countHistory();
    Integer findHistoryByHistoryId(Integer historyId);
}
