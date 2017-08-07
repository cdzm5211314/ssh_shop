package cn.itcast.shop.index.action;

import java.util.List;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.shop.category.entity.Category;
import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.product.entity.Product;
import cn.itcast.shop.product.service.ProductService;

/**
 * 
 * @ClassName: IndexAction
 * @Description: 首页访问的Action 
 * @Author: ChenD
 * @CreateDate: Aug 3, 2017 10:13:35 AM
 */
public class IndexAction extends ActionSupport{
	
	//注入categoryService
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//注入productService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	/**
	 * 访问首页的方法
	 */
	public String execute(){
		//查询一级分类的所有数据
		List<Category> clist = categoryService.findAll();
		//每个页面都有这个数据,所以一级分类数据存到session中
		ActionContext.getContext().getSession().put("clist", clist);
		//查询热门商品的所有数据信息
		// is_hot  o:表示不是热门	 1:表示是热门  ,页面显示最多10个
		List<Product> hlist = productService.findHot();
		//将查询的数据放到值栈中
		ActionContext.getContext().getValueStack().set("hlist", hlist);
		
		return  "indexSUCCESS";
	}  
	
}
