/**
 * 
 */
package cn.itcast.shop.order.service;

import javax.lang.model.type.PrimitiveType;

import cn.itcast.shop.order.dao.OrderDao;

/**
 * @ClassName: OrderService
 * @Description: 订单模块业务逻辑层 
 * @Author: ChenD
 * @CreateDate: Aug 22, 2017 1:45:49 PM
 */
public class OrderService {
	
	//注入OrderDao
	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	
	
}
