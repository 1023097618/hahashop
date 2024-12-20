package com.mall.service.impl;


import com.mall.common.StateChangeUtil;
import com.mall.common.StateEnum;
import com.mall.common.TransformUtil;
import com.mall.dao.GoodDao;
import com.mall.dao.HistoryDao;
import com.mall.entity.Good;
import com.mall.entity.History;
import com.mall.service.GoodService;
import com.mall.service.HistoryService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.mall.common.StateEnum.ACTIVE;
import static com.mall.common.StateEnum.SOLD_OUT;

@Service
public class GoodServiceImpl implements GoodService {

    @Resource
    private GoodDao goodDao;
    @Resource
    private HistoryDao historyDao;
    @Resource
    private StateChangeUtil stateChangeUtil;
    @Resource
    private HistoryService historyService;
    @Resource
    private TransformUtil transformUtil;

    @Value("${file.baseurl}") private String baseUrl;

    @Override
    public List<Good> goodList(Integer pageSize, Integer pageNum, String goodName, Integer categoryId, Integer privilege) {
        List<Good> goods = goodDao.goodList(pageSize, pageNum, goodName, categoryId, privilege);
        for(Good good : goods){
            good.setGoodImage(transformUtil.stringToStringArray(good.getGoodImage()));
        }
        return goods;
    }

    @Override
    public Good getGoodById(Integer id) {
        return goodDao.getGoodById(id);
    }

    @Override
    public Integer countGoods(Integer privilege, String goodName, Integer categoryId) {
        return goodDao.countGoods( privilege, goodName, categoryId);
    }

    @Override
    public String getDetail(Integer goodId){
        return goodDao.getDetail(goodId);
    }



    @Override
    public Boolean addGood(Good good) {

        String[] images = Arrays.stream(good.getGoodImage())  // 将String[]转为Stream
                .map(image -> image.replace(baseUrl, ""))     // 对每个String元素进行replace操作
                .toArray(String[]::new);
        good.setGoodImage(images);
        String goodImages = transformUtil.stringArrayToString(images);

        good.setBuyerNum(0);
        if(good.getGoodNum() > 0) { good.setGoodState(stateChangeUtil.StateChange(ACTIVE)); }
        else{ good.setGoodState(stateChangeUtil.StateChange(SOLD_OUT)); }

        Integer rowsAffected = goodDao.addGood(good, goodImages);
        if(rowsAffected > 0){//插入成功就加记录
            historyService.addHistory(good, good.getGoodImage()[0]);
        }
        return rowsAffected > 0;
    }

    @Override
    public Boolean updateGood(Good good) {

        String goodImage = transformUtil.stringArrayToString(good.getGoodImage());
        if(good.getGoodNum() > 0) { good.setGoodState(stateChangeUtil.StateChange(ACTIVE)); }
        else{ good.setGoodState(stateChangeUtil.StateChange(SOLD_OUT)); }

        Integer rowsAffected = goodDao.updateGood(good, goodImage);
        good = goodDao.getGoodById(good.getGoodId());
        if(rowsAffected > 0){//插入成功就加记录
            historyService.addHistory(good, good.getGoodImage()[0]);
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

    @Override
    public Boolean goodNumChange(Integer goodId, Integer changeGoodNum) {
        return goodDao.goodNumChange(goodId, changeGoodNum) >0;
    }
}
