package com.mall.service.impl;


import com.mall.dao.GoodDao;
import com.mall.entity.Good;
import com.mall.service.GoodService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Resource
    private GoodDao goodDao;

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
        if(rowsAffected > 0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateGood(Good good) {
        Integer rowsAffected = goodDao.updateGood(good);
        if(rowsAffected > 0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteGood(Integer goodId) {
        Integer rowsAffected = goodDao.deleteGood(goodId);
        if(rowsAffected > 0){
            return true;
        }
        return false;
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
