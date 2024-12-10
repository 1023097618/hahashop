package com.mall.service;

import com.mall.entity.Collect;

import java.util.List;

public interface CollectService {
    Boolean addCollect(Collect collect);
    List<Integer> getCollectGoods(Integer pageSize, Integer pageNum, Integer userId);
    Integer countCollect(Integer userId);
    Boolean deleteCollect(Integer userId, Integer goodId);
    Boolean collectSelect(Integer userId, Integer goodId);
}
