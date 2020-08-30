package com.jiaming.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiaming.category.service.CategoryService;

import povos.CategoryPovo;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	//显示类别模块
	@GetMapping("/loadCategory.action")
	public String loadCategory(Model model) {
		
		List<CategoryPovo> povos = categoryService.getCategoryPovos();
		
		model.addAttribute("categorypovos", povos);
		
		return "jsps/left";
	}
}
