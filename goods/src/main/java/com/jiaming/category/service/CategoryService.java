package com.jiaming.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiaming.mapper.CategoryMapper;

import povos.CategoryPovo;

@Service
public class CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;

	public List<CategoryPovo> getCategoryPovos() {
		//获取分类一级目录
		List<CategoryPovo> povos = categoryMapper.selectCategoryiesPidIsNull();
		
		//获取二级目录
		for (CategoryPovo categoryPovo : povos) {
			categoryPovo.setChildren(categoryMapper.selectCategoryiesByPid(categoryPovo.getCid()));
		}
		
		return povos;
	}
	
	
}
