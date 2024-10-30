package com.mall.dao;

import com.mall.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryDao {//暂时无用
    List<Category> findByCategoryPid(@Param("pid") Integer pid);
}
