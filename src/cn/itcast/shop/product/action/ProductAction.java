/**
 * 
 */
package cn.itcast.shop.product.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.product.entity.Product;
import cn.itcast.shop.product.service.ProductService;

/**
 * @ClassName: ProductAction
 * @Description: 商品信息表现层 
 * @Author: ChenD
 * @CreateDate: Aug 7, 2017 4:02:25 PM
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product>{

	//模型驱动
	private Product product = new Product();
	public Product getModel() {
		return product;
	}
	
	//注入productService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	//根据id查看商品的详细信息
	public String findById(){
		
		product = productService.findById(product.getPid());
//		Product p = productService.findById(product.getPid());
//		ServletActionContext.getRequest().setAttribute("product", p);
		return "findByIdSUCCESS";
	}
}
