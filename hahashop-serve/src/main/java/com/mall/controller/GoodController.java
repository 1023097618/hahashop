package com.mall.controller;

import com.mall.common.Result;
import com.mall.common.ResultUtil;
import com.mall.entity.Good;
import com.mall.entity.Order;
import com.mall.service.GoodService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mall.common.ResultEnum.*;

@RestController
@RequestMapping("/good")
public class GoodController {

    @Resource
    private GoodService goodService;

    @RequestMapping("/list")
    //获取所有商品信息
    public Result goodList(@RequestParam Integer pageSize, @RequestParam Integer pageNum, HttpServletRequest request) {
        List<Good> goodList = goodService.goodList(pageSize, pageNum);
        Integer totalGoods = goodService.countGoods();

        Map<String, Object> data = new HashMap<>();
        // 遍历 Cookie，找到 X-Hahashop-Token
        Boolean isTokenValid = (Boolean) request.getAttribute("isTokenValid");
        if (isTokenValid != null && !isTokenValid) {
            for (Good good : goodList) {
                good.setBuyerNum(0);
            }
        }
        data.put("goods", goodList);
        data.put("totalGoods", totalGoods);

        if (goodList != null) {
            return ResultUtil.success(SUCCESS, data);
        } else {
            return ResultUtil.error(GOOD_NOT_EXIST);
        }
    }

    @RequestMapping("/detail")
    public Result<Object> goodDetail(@RequestParam Integer id) {
        String goodDesc = goodService.getDetail(id);
        Map<String, Object> data = new HashMap<>();
        data.put("goodDesc", goodDesc);

        if (goodDesc != null) {
            return ResultUtil.success(SUCCESS, data);
        } else {
            return ResultUtil.error(UNKNOWN_ERROR);
        }
    }

    @RequestMapping("/update")
    public Result<Object> goodUpdate(HttpServletRequest request, @RequestBody Good good) {
        Boolean isTokenValid = (Boolean) request.getAttribute("isTokenValid");
        if (isTokenValid != null && isTokenValid) {
            if (goodService.getGoodById(good.getGoodId()) == null) {
                return ResultUtil.error(GOOD_NOT_EXIST);
            } else if (goodService.updateGood(good)) {
                return ResultUtil.success(SUCCESS, null);
            } else {
                return ResultUtil.error(UNKNOWN_ERROR);
            }
        } else {
            return ResultUtil.error(ILLEGAL_TOKEN);
        }
    }

    @RequestMapping("/add")
    public Result<Object> goodAdd(HttpServletRequest request, @RequestBody Good good) {
        Boolean isTokenValid = (Boolean) request.getAttribute("isTokenValid");
        if (isTokenValid != null && isTokenValid) {
            if (goodService.addGood(good)) {
                return ResultUtil.success(SUCCESS, null);
            } else {
                return ResultUtil.error(UNKNOWN_ERROR);
            }
        } else {
            return ResultUtil.error(ILLEGAL_TOKEN);
        }
    }

    @RequestMapping("/delete")
    public Result<Object> goodDelete(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        Integer goodId = Integer.valueOf(map.get("goodId").toString());
        if (goodService.getGoodById(goodId) == null) {
            return ResultUtil.error(GOOD_NOT_EXIST);
        } else if (goodService.deleteGood(goodId)) {
            return ResultUtil.success(SUCCESS, null);
        } else {
            return ResultUtil.error(UNKNOWN_ERROR);
        }

    }
}

