package com.mall.service.impl;


import com.mall.dao.GoodDao;
import com.mall.dao.HistoryDao;
import com.mall.entity.Good;
import com.mall.entity.History;
import com.mall.service.GoodService;
import com.mall.service.HistoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Resource
    private GoodDao goodDao;
    @Resource
    private HistoryDao historyDao;
    @Resource
    private HistoryService historyService;

    @Override
    public List<Good> goodList(Integer pageSize, Integer pageNum) {
        return goodDao.goodList(pageSize,pageNum);
    }

    @Override
    public Good getGoodById(Integer id) {
        return goodDao.getGoodById(id);
    }

    @Override
    public Integer countGoods(){
        return goodDao.countGoods();
    }

    @Override
    public String getDetail(Integer goodId){
        return goodDao.getDetail(goodId);
    }

    @Override
    public Boolean addGood(Good good) {
        good.setBuyerNum(0);
        good.setGoodState(0);
        Integer rowsAffected = goodDao.addGood(good);
        if(rowsAffected > 0){//插入成功就加记录
            historyService.addHistory(good);
        }
        return rowsAffected > 0;
    }

    @Override
    public Boolean updateGood(Good good) {
        Integer rowsAffected = goodDao.updateGood(good);

        good = goodDao.getGoodById(good.getGoodId());
        if(rowsAffected > 0){//插入成功就加记录
            historyService.addHistory(good);
        }

        return rowsAffected > 0;
    }

    @Override
    public Boolean deleteGood(Integer goodId) {
        Integer rowsAffected = goodDao.deleteGood(goodId);
        return rowsAffected > 0;
    }

    @Override
    public Boolean stateChange(Integer goodId, Integer goodState) {
        try{
            Integer rowsAffected = goodDao.stateChange(goodId,goodState);
            if(rowsAffected > 0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


}
