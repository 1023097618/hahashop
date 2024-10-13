package com.mall.service;

import com.mall.entity.Good;
import com.mall.entity.History;

import java.util.List;


public interface HistoryService {
    void addHistory(Good good);
    Integer deleteHistory(Integer historyId);
    List<History> getHistory(Integer pageSize, Integer pageNum);
    Integer countHistory();
}
