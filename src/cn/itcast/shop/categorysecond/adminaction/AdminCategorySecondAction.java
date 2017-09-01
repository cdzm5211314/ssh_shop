/**
 * 
 */
package cn.itcast.shop.categorysecond.adminaction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

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
	
	
	
}
