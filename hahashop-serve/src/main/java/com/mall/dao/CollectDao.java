package com.mall.dao;


import com.mall.entity.Collect;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectDao {
    Integer addCollect(Collect collect);
    List<Integer> getCollectGoods(Integer pageSize, Integer pageNum, Integer userId);
    Integer countCollect(Integer userId);
    Integer deleteCollect(Integer userId, Integer goodId);
    Integer collectSelect(Integer userId, Integer goodId);
}
