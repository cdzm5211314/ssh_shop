/**
 * 
 */
package cn.itcast.shop.category.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.category.entity.Category;
import cn.itcast.shop.category.service.CategoryService;

/**
 * @ClassName: AdminCategoryAction
 * @Description: 后台一级分类操作 表现层
 * @Author: ChenD
 * @CreateDate: Sep 1, 2017 9:14:21 AM
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {
	
	private Category category = new Category();
	public Category getModel() {
		return category;
	}
	
	//注入一级分类的service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//后台查询所有一级分类
	public String findAll(){
		List<Category> cList = categoryService.findAll();
		//将集合数据显示到页面上
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAllSUCCESS";
	}
	
	
	
}
