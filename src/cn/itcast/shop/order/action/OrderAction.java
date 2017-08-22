/**
 * 
 */
package cn.itcast.shop.order.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.order.enity.Order;
import cn.itcast.shop.order.service.OrderService;

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
	

	
	
	
}
