/**
 * 
 */
package cn.itcast.shop.order.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.order.entity.Order;

/**
 * @ClassName: OrderDao
 * @Description: 订单模块数据访问层 
 * @Author: ChenD
 * @CreateDate: Aug 22, 2017 1:46:54 PM
 */
public class OrderDao extends HibernateDaoSupport {

	/**
	 * @方法的名称: save
	 * @Description: 保存订单数据到数据库
	 * @Author: chenD
	 * @CreateDate: Aug 22, 2017 3:43:03 PM
	 * @param order void
	 */
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}
	
	
	
}
