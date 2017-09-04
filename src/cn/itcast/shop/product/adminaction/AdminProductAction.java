/**
 * 
 */
package cn.itcast.shop.product.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.categorysecond.entity.CategorySecond;
import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.product.entity.Product;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.utils.PageBean;

/**
 * @ClassName: AdminProductAction
 * @Description: 后台商品管理的表现层 
 * @Author: ChenD
 * @CreateDate: Sep 4, 2017 9:24:58 AM
 */
public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {

	private Product product = new Product();
	public Product getModel() {
		return product;
	}
	
	//注入service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	//接受finAll方法传递过来的参数page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}

	//查询所有商品信息并分页显示
	public String findAll(){
		//调用service
		PageBean<Product> pageBean = productService.findByPage(page);
		//将数据传递到页面上
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllSUCCESS";
	}
	//点击"添加"按钮跳转到添加商品页面
	public String addPage(){
		//需要查询所有二级分类集合
		List<CategorySecond> csList = categorySecondService.findAll();
		//保存数据到值栈
		ActionContext.getContext().getValueStack().set("csList", csList);
		
		return "addPageSUCCESS";
	}
	
	
	

}
