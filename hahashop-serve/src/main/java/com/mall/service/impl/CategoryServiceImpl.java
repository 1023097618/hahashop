package com.mall.service.impl;

import com.mall.dao.CategoryDao;
import com.mall.entity.Category;
import com.mall.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {//暂时不用
    @Resource
    private CategoryDao categoryDao;

    @Override
    public List<Category> findByCategoryPid(Integer pid) { return categoryDao.findByCategoryPid(pid); }
}
