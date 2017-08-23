/**
 * 
 */
package cn.itcast.shop.order.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.aspectj.weaver.AjAttribute.PrivilegedAttribute;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.cart.entity.Cart;
import cn.itcast.shop.cart.entity.CartItem;
import cn.itcast.shop.order.entity.Order;
import cn.itcast.shop.order.entity.OrderItem;
import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.user.entity.User;
import cn.itcast.shop.user.service.UserService;
import cn.itcast.shop.utils.PageBean;
import cn.itcast.shop.utils.PageHibernateCallback;

/**
 * @ClassName: OrderAction
 * @Description: 订单模块表现层 
 * @Author: ChenD
 * @CreateDate: Aug 22, 2017 1:42:03 PM
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	
	private Order order =  new Order();
	public Order getModel() {
		return order;
	}
	//注入OrderService
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}


	/*
	 * 我的订单的查询
	 */
	public String findByUid(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(),page);
		//将分页数据显示到页面上,通过值栈显示
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSUCCESS";
	}
	/*
	 * 根据订单id查询订单
	 */
	public String findByOid(){
		order = orderService.findByOid(order.getOid());
		return "findByOidSUCCESS";
	}
	
	/*
	 * 购物车页面点击"提交订单"按钮跳转到到订单页面
	 */
	public  String save(){
		// 1.保存到订单数据到数据库
		order.setOrdertime(new Date());
		order.setState(1); //1 表示未付款 , 2 表示已经付款,未发货, 3 已发货,未确认收获 , 4 交易完成
		//总计数据是购物车的总计
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			this.addActionError("亲,你还满意购物,请先去购物...");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		//设置订单中的订单项
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
		}
		//设置订单所属用户
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (user == null ) {
			this.addActionError("亲,你还没有登录,请先去登录...");
			return "login";
		}
		order.setUser(user);
		orderService.saveOrder(order);
		// 2.将订单数据显示到页面
		//通过值栈方式显示到到订单页面,应为模型驱动Order的对象本身就在值栈的栈顶,所以可以在页面直接操作
		
		//3. 清空购物车
		cart.clearCart();
		return "saveSUCCESS";
	}
	
	
}
