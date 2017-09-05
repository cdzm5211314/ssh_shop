/**
 * 
 */
package cn.itcast.shop.order.adminorder;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.order.entity.Order;
import cn.itcast.shop.order.entity.OrderItem;
import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.utils.PageBean;

/**
 * @ClassName: AdminOrderAction
 * @Description: 后台订单管理表现层 
 * @Author: ChenD
 * @CreateDate: 2017年9月4日 下午3:42:29
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {

	private Order order = new Order();
	public Order getModel() {
		return order;
	}
	
	//注入service
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//接受page参数
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	//查询所有订单,分页显示
	public String findAll(){
		
		PageBean<Order> pageBean = orderService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllSUCCESS";
	}
	//根据订单id查询订单项
	public String findOrderItem(){
		
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		//显示到值栈
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItemSUCCESS";
	}
	
	
}
