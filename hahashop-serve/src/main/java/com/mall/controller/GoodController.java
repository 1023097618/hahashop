package com.mall.controller;

import com.mall.common.JwtTokenUtil;
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
    public Result goodList(HttpServletRequest request,@RequestParam Integer pageSize, @RequestParam Integer pageNum) {
        List<Good> goodList = goodService.goodList(pageSize, pageNum);
        Integer totalGoods = goodService.countGoods();
        String token = request.getHeader("X-hahashop-token");
        Map<String, Object> data = new HashMap<>();
        if(token!=null&&JwtTokenUtil.decodeToken(token)!=null){}
        else if (token!=null&& JwtTokenUtil.decodeToken(token)==null) {
            return ResultUtil.error(ILLEGAL_TOKEN);
        } else {
            for (Good good : goodList) {
                good.setBuyerNum(0);
            }
        }

        data.put("goods", goodList);
        data.put("totalGoods", totalGoods);
        return ResultUtil.success(SUCCESS, data);
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
    public Result<Object> goodUpdate(@RequestBody Good good) {
        if (goodService.getGoodById(good.getGoodId()) == null) {
            return ResultUtil.error(GOOD_NOT_EXIST);
        } else if (goodService.updateGood(good)) {
            return ResultUtil.success(SUCCESS, null);
        } else {
            return ResultUtil.error(UNKNOWN_ERROR);
        }
    }

    @RequestMapping("/add")
    public Result<Object> goodAdd(@RequestBody Good good) {
        if (goodService.addGood(good)) {
            return ResultUtil.success(SUCCESS, null);
        } else {
            return ResultUtil.error(UNKNOWN_ERROR);
        }
    }

    @RequestMapping("/delete")
    public Result<Object> goodDelete(@RequestBody Map<String, Object> map) {
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

