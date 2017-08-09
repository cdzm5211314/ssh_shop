/**
 * 
 */
package cn.itcast.shop.product.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.category.entity.Category;
import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.product.entity.Product;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.utils.PageBean;

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
	
	//接收分类的id,属性驱动
	private Integer cid;
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getCid() {
		return cid;
	}
	//接收当前页数
	private int  page;
	public void setPage(int page) {
		this.page = page;
	}

	//注入productService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	//注入CategoryService
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//根据分类id查询商品的信息
	public String findByCid(){
		//查询所有的一级分类(之前在session中保存了一级分类的的信息了,想使用直接在页面取值)
		//List<Category> clist = categoryService.findAll();
		//根据一级分类查询商品,带分页查询
		PageBean<Product> pageBean = productService.findByPageCid(cid,page); 
		//页面要获取PageBean,需要把PageBean保存到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return  "findByCidSUCCESS";
	}

	//根据id查看商品的详细信息
	public String findById(){
		
		product = productService.findById(product.getPid());
//		Product p = productService.findById(product.getPid());
//		ServletActionContext.getRequest().setAttribute("product", p);
		return "findByIdSUCCESS";
	}
	
}
