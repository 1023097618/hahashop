package com.mall.service.impl;

import com.mall.dao.HistoryDao;
import com.mall.entity.Good;
import com.mall.entity.History;

import com.mall.service.HistoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class HistroyServiceImpl implements HistoryService {
    @Resource
    private HistoryDao histroyDao;

    @Override
    public void addHistory(Good good, String goodImage) {
        History history = new History();
        history.setGoodId(good.getGoodId());
        history.setGoodName(good.getGoodName());
        history.setGoodPrice(good.getGoodPrice());
        history.setGoodDesc(good.getGoodDesc());
        history.setGoodImage(goodImage);
        // 创建日期对象
        Date date = new Date();
        // 使用 SimpleDateFormat 格式化日期为"yyyy-MM-dd"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String formattedDate = sdf.format(date);
        history.setOperationTime(formattedDate);
        histroyDao.addHistory(history);
    }

    @Override
    public Integer deleteHistory(Integer id) { return histroyDao.deleteHistory(id);}

    @Override
    public List<History> getHistory(Integer pageSize, Integer pageNum) {
        List<History> historyList = histroyDao.getHistory(pageSize, pageNum);
        historyList.sort(Comparator.comparing(History::getOperationTime).reversed());
        return historyList;
    }

    @Override
    public Integer countHistory() {
        return histroyDao.countHistory();
    }

    @Override
    public Boolean findHistoryById(Integer historyId) { return histroyDao.findHistoryByHistoryId(historyId) > 0;}
}
