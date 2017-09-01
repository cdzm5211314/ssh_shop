/**
 * 
 */
package cn.itcast.shop.categorysecond.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.category.entity.Category;
import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.categorysecond.entity.CategorySecond;
import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.utils.PageBean;

/**
 * @ClassName: AdminCategorySecondAction
 * @Description: 后台二级分类管理表现层 
 * @Author: ChenD
 * @CreateDate: Sep 1, 2017 12:46:27 PM
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {
	
	private CategorySecond categorySecond = new CategorySecond();
	public CategorySecond getModel() {
		return categorySecond;
	}
	
	//注入CategorySecondService
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	//注入一级分类service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//接受pege
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}

	//查询所有二级分类(分页)
	public String findAll(){
		System.err.println(page);
		PageBean<CategorySecond>  pageBean = categorySecondService.findByPage(page);
		//将pageBean数据保存到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllSUCCESS";
	}
	
	//跳转到添加二级分类页面
	public String addPage(){
		//先查询所有一级分类在页面显示列表
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList",cList);
		return "addPageSUCCESS";
	}
	//添加二级分类
	public String save(){
		
		categorySecondService.save(categorySecond);
		return "saveSUCCESS";
	}
	
}
