package com.mall.controller;

import com.mall.common.*;
import com.mall.entity.Category;
import com.mall.entity.Good;
import com.mall.entity.Order;
import com.mall.entity.User;
import com.mall.service.AuthService;
import com.mall.service.CategoryService;
import com.mall.service.GoodService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mall.common.ResultEnum.*;

@RestController
@RequestMapping("/api/good")
public class GoodController {

    @Resource
    private AuthService authService;
    @Resource
    private GoodService goodService;
    @Resource
    private StateChangeUtil stateChangeUtil;
    @Resource
    private CategoryService categoryService;
    @Autowired
    private CheckUtil checkUtil;

    @RequestMapping("/list")
    //获取所有商品信息
    public Result goodList(HttpServletRequest request,
                           @RequestParam Integer pageSize, @RequestParam Integer pageNum,
                           @RequestParam(required = false) String goodName, @RequestParam(required = false) Integer categoryId) {

        String token = request.getHeader("x-hahashop-token");
        Map<String, Object> data = new HashMap<>();
        if(token == null || JwtTokenUtil.decodeToken(token) != null){

            User user = new User();
            if (token != null){
                user = (User) JwtTokenUtil.decodeToken(token);
                user = authService.login(user.getUsername());
            }

            try{
                List<Good> goodList = goodService.goodList(pageSize, pageNum, goodName, categoryId, user.getPrivilege());
                Integer totalGoods = goodService.countGoods(user.getPrivilege());
                if(token == null || user.getPrivilege() == 2){
                    for (Good good : goodList) {
                        good.setBuyerNum(0);
                    }
                }
                data.put("goods", goodList);
                data.put("totalGoods", totalGoods);
            }catch (Exception e){
                return ResultUtil.error(EXAMPLE_NOT_EXIST);
            }
        return ResultUtil.success(SUCCESS, data);}


        else if (JwtTokenUtil.decodeToken(token) == null) {
            return ResultUtil.error(ILLEGAL_TOKEN);
        }
        return ResultUtil.error(UNKNOWN_ERROR);
    }

    @RequestMapping("/detail")
    public Result<Object> goodDetail(@RequestParam Integer id) {
        User user = checkUtil.tookenCheck();
        if(user!=null && (user.getPrivilege()!=1 && user.getPrivilege()!=2)){ return ResultUtil.error(ILLEGAL_TOKEN); }
        String goodDesc = goodService.getDetail(id);
        Map<String, Object> data = new HashMap<>();
        data.put("goodDesc", goodDesc);

        if (goodDesc != null) {
            return ResultUtil.success(SUCCESS, data);
        } else {
            return ResultUtil.error(EXAMPLE_NOT_EXIST);
        }
    }

    @RequestMapping("/update")
    public Result<Object> goodUpdate(@RequestBody Good good) {
        User user = checkUtil.tookenCheck();

        if(user!=null && user.getPrivilege() != 1){ return ResultUtil.error(ILLEGAL_TOKEN); }
        if (goodService.getGoodById(good.getGoodId()) == null) {
            return ResultUtil.error(EXAMPLE_NOT_EXIST);
        }
        if (goodService.updateGood(good)) {
            return ResultUtil.success(SUCCESS, null);
        } else {
            return ResultUtil.error(UNKNOWN_ERROR);
        }
    }

    @RequestMapping("/add")
    public Result<Object> goodAdd(@RequestBody Good good) {
        User user = checkUtil.tookenCheck(); System.out.println(good.getGoodNum());
        if(user!=null && user.getPrivilege() != 1){ return ResultUtil.error(ILLEGAL_TOKEN); }
        if (goodService.addGood(good)) {
            return ResultUtil.success(SUCCESS, null);
        } else {
            return ResultUtil.error(UNKNOWN_ERROR);
        }
    }

    @RequestMapping("/delete")
    public Result<Object> goodDelete(@RequestBody Map<String, Object> map) {
        User user = checkUtil.tookenCheck();
        if(user!=null && user.getPrivilege() != 1){ return ResultUtil.error(ILLEGAL_TOKEN); }

        Integer goodId = Integer.valueOf(map.get("goodId").toString());
        if (goodService.getGoodById(goodId) == null) {
            return ResultUtil.error(EXAMPLE_NOT_EXIST);
        } else if (goodService.deleteGood(goodId)) {
            return ResultUtil.success(SUCCESS, null);
        } else {
            return ResultUtil.error(UNKNOWN_ERROR);
        }
    }

    @RequestMapping("/category")
    public Result<Object> goodCategory() {
        try{
            Map<String, Object> data = new HashMap<>();
            List<Map<String, Object>> categoryList = new ArrayList<>();
            List<Category> fathers = categoryService.findByCategoryPid(0);

            for (Category category : fathers) {
                Map<String, Object> fatherMap = new HashMap<>();//父，一个父级一个
                fatherMap.put("label", category.getCategoryName());
                fatherMap.put("value", category.getCategoryId());
                List<Map<String, Object>> children = new ArrayList<>();//子级
                List<Category> child = categoryService.findByCategoryPid(category.getCategoryId());//父级id是子级的pid
                for (Category childCategory : child) {
                    Map<String, Object> childMap = new HashMap<>();
                    childMap.put("label", childCategory.getCategoryName());
                    childMap.put("value", childCategory.getCategoryId());
                    children.add(childMap);
                }
                fatherMap.put("children", children);//对应的子级置入
                categoryList.add(fatherMap);//
                data.put("categoryList", categoryList);
            }
            return ResultUtil.success(SUCCESS, data);
        }catch (Exception e){
            return ResultUtil.error(EXAMPLE_NOT_EXIST);
        }
    }
}

