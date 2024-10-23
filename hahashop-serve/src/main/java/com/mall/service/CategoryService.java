package com.mall.service;

import com.mall.entity.Category;

import java.util.List;

public interface CategoryService {//暂时不用
    List<Category> findByCategoryPid(Integer pid);
}
