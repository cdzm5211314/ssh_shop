/**
 * 
 */
package cn.itcast.shop.order.service;

import javax.lang.model.type.PrimitiveType;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.dao.OrderDao;
import cn.itcast.shop.order.entity.Order;

/**
 * @ClassName: OrderService
 * @Description: 订单模块业务逻辑层 
 * @Author: ChenD
 * @CreateDate: Aug 22, 2017 1:45:49 PM
 */
@Transactional
public class OrderService {
	
	//注入OrderDao
	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	/**
	 * @方法的名称: saveOrder
	 * @Description: 保存订单的业务层操作
	 * @Author: chenD
	 * @CreateDate: Aug 22, 2017 3:42:12 PM
	 * @param order void
	 */
	public void saveOrder(Order order) {
		orderDao.save(order);
	}
	
	
	
}
