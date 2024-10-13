package com.mall.controller;

import com.mall.common.Result;
import com.mall.common.ResultUtil;
import com.mall.entity.History;
import com.mall.service.HistoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mall.common.ResultEnum.*;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Resource
    private HistoryService historyService;

    @RequestMapping("/list")
    public Result<Object> getHistory(@RequestParam Integer pageSize, @RequestParam Integer pageNum) {
        List<History> historyList = historyService.getHistory(pageSize, pageNum);
        Integer totalHistories =  historyService.countHistory();

        Map<String, Object> data = new HashMap<>();
        data.put("totalHistories", totalHistories);
        data.put("histories", historyList);

        return ResultUtil.success(SUCCESS,data);
    }

    @RequestMapping("/delete")
    public Result<Object> delete(@RequestBody History history) {
        Integer rowsAffected = historyService.deleteHistory(history.getHistoryId());
        if (rowsAffected > 0) {
            return ResultUtil.success(SUCCESS,null);
        }else{
            return ResultUtil.error(HISTORY_NOT_EXIST);
        }
    }
}
