/**
 * 
 */
package cn.itcast.shop.product.adminaction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

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
	
	
	

}
