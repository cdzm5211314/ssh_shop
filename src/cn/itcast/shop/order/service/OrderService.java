/**
 * 
 */
package cn.itcast.shop.order.service;

import java.util.List;

import javax.lang.model.type.PrimitiveType;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.dao.OrderDao;
import cn.itcast.shop.order.entity.Order;
import cn.itcast.shop.utils.PageBean;

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
	/**
	 * @方法的名称: findByPageUid
	 * @Description: 我的订单查询业务层
	 * @Author: chenD
	 * @CreateDate: Aug 23, 2017 10:08:52 AM
	 * @param uid
	 * @param page
	 * @return PageBean<Order>
	 */
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示的记录数
		Integer limit = 5;
		pageBean.setLimit(limit);
		//设置总记录数
		Integer totalCount = null ;
		totalCount = orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		//总的页数
		Integer totalPage = null;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页显示数据的集合
		Integer begin = (page - 1 ) * limit;
		List<Order> list = orderDao.findByPageUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	/**
	 * @方法的名称: findByOid
	 * @Description: 根基订单id查询订单
	 * @Author: chenD
	 * @CreateDate: Aug 23, 2017 2:39:20 PM
	 * @param oid
	 * @return Order
	 */
	public Order findByOid(Integer oid) {
		return orderDao.finyByOid(oid);
	}
	
	
}
