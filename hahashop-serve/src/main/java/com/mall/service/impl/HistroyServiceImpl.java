package com.mall.service.impl;

import com.mall.dao.HistoryDao;
import com.mall.entity.Good;
import com.mall.entity.History;

import com.mall.service.HistoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class HistroyServiceImpl implements HistoryService {
    @Resource
    private HistoryDao histroyDao;

    @Override
    public void addHistory(Good good) {
        History history = new History();
        history.setGoodId(good.getGoodId());
        history.setGoodName(good.getGoodName());
        history.setGoodPrice(good.getGoodPrice());
        history.setGoodDesc(good.getGoodDesc());
        history.setGoodImage(good.getGoodImage());
        // 创建日期对象
        Date date = new Date();
        // 使用 SimpleDateFormat 格式化日期为"yyyy-MM-dd"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String formattedDate = sdf.format(date);
        history.setOperationTime(formattedDate);
        histroyDao.addHistory(history);
    }

    @Override
    public Integer deleteHistory(Integer id) {
        return histroyDao.deleteHistory(id);
    }

    @Override
    public List<History> getHistory(Integer pageSize, Integer pageNum) {
        return histroyDao.getHistory(pageSize, pageNum);
    }

    @Override
    public Integer countHistory() {
        return histroyDao.countHistory();
    }
}
