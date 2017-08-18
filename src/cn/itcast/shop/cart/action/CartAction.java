/**
 * 
 */
package cn.itcast.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.shop.cart.entity.Cart;
import cn.itcast.shop.cart.entity.CartItem;
import cn.itcast.shop.product.entity.Product;
import cn.itcast.shop.product.service.ProductService;

/**
 * @ClassName: CartAction
 * @Description: 购物车表现层
 * @Author: ChenD
 * @CreateDate: Aug 18, 2017 12:58:00 PM
 */
public class CartAction extends ActionSupport {

	// 接受页面获取到的参数 商品的pid及商品的数量
	private Integer pid;
	private Integer count;
	// 注入商品的service
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	/*
	 * 购物项添加到购物车中
	 */
	public String addCart() {
		// 封装一个CartItem对象
		CartItem cartItem = new CartItem();
		// 设置购物项的商品数量
		cartItem.setCount(count);
		// 根据商品的pid查询商品
		Product product = productService.findById(pid);
		// 设置商品
		cartItem.setProduct(product);
		// 将购物项添加到购物车
		// 购物车应该在session中
		Cart cart = getCart();
		cart.addCart(cartItem);
		
		return "addCartSUCCESS";
	}

	/**
	 * @方法的名称: getCart
	 * @Description: 获取购物车方法,从session中获得购物车
	 * @Author: chenD
	 * @CreateDate: Aug 18, 2017 1:28:58 PM
	 * @return Cart
	 */
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			// 第一次获取购物车不存在,就创建一个并保存到session中
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}

}
