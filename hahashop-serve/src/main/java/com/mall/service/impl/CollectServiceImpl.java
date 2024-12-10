package com.mall.service.impl;


import com.mall.dao.CollectDao;
import com.mall.entity.Collect;
import com.mall.service.CollectService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Resource
    private CollectDao collectDao;

    @Override
    public Boolean addCollect(Collect collect) {
        return collectDao.addCollect(collect) > 0;
    }

    @Override
    public List<Integer> getCollectGoods(Integer pageSize, Integer pageNum, Integer userId) {
        return collectDao.getCollectGoods(pageSize, pageNum, userId);
    }

    @Override
    public Integer countCollect(Integer userId) {
        return collectDao.countCollect(userId);
    }

    @Override
    public Boolean deleteCollect(Integer userId, Integer goodId) {
        return collectDao.deleteCollect(userId, goodId) > 0;
    }

    @Override
    public Boolean collectSelect(Integer userId, Integer goodId){ return collectDao.collectSelect(userId, goodId) > 0; }
}
