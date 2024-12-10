package com.mall.controller;


import com.mall.common.CheckUtil;
import com.mall.common.Result;
import com.mall.common.ResultEnum;
import com.mall.common.ResultUtil;
import com.mall.entity.Collect;
import com.mall.entity.Good;
import com.mall.entity.User;
import com.mall.service.CollectService;
import com.mall.service.GoodService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/collect")
public class CollectionController {

    @Resource
    private CheckUtil checkUtil;
    @Resource
    private CollectService collectService;
    @Resource
    private GoodService goodService;

    @RequestMapping("/add")
    public Result<Object> add(@RequestBody Map<String, Object> map) {
        User user = checkUtil.tookenCheck();
        if(user == null || user.getPrivilege()!=2){ return ResultUtil.error(ResultEnum.ILLEGAL_TOKEN); }
        Collect collect = new Collect();
        collect.setUserId(user.getUserId());
        collect.setGoodId((Integer) map.get("goodId"));
        if(!collectService.addCollect(collect)){
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
        }
        return ResultUtil.success(ResultEnum.SUCCESS,null);
    }

    @RequestMapping("/list")
    public Result<Object> collectList(@RequestParam Integer pageSize, @RequestParam Integer pageNum) {
        User user = checkUtil.tookenCheck();
        if(user == null){ return ResultUtil.error(ResultEnum.ILLEGAL_TOKEN); }
        Integer userId = user.getUserId();

        List<Integer> goodIds = collectService.getCollectGoods(pageSize, pageNum, userId);
        Map<String, Object> data = new HashMap<>();
        List<Map<String, Object>> goods = new ArrayList<>();

        for(Integer goodId : goodIds){
            Map<String, Object> thisGood = new HashMap<>();
            Good good = goodService.getGoodById(goodId);
            thisGood.put("goodName", good.getGoodName());
            thisGood.put("goodPrice", good.getGoodPrice());
            thisGood.put("goodId", goodId);
            thisGood.put("goodImage", good.getGoodImage());
            goods.add(thisGood);
        }
        Integer totalGoods = collectService.countCollect(userId);
        data.put("totalGoods", totalGoods);
        data.put("goods", goods);
        return ResultUtil.success(ResultEnum.SUCCESS,data);
    }

    @RequestMapping("/delete")
    public Result<Object> delete(@RequestBody Map<String, Object> map) {
        User user = checkUtil.tookenCheck();
        if(user == null || user.getPrivilege()!=2){ return ResultUtil.error(ResultEnum.ILLEGAL_TOKEN); }

        Integer userId = user.getUserId();
        Integer goodId = (Integer) map.get("goodId");

        if(collectService.deleteCollect(userId, goodId)){
            return ResultUtil.success(ResultEnum.SUCCESS,null);
        }else {
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
        }
    }



}
