/**
 * 
 */
package cn.itcast.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.order.entity.Order;
import cn.itcast.shop.utils.PageHibernateCallback;

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

	/**
	 * @方法的名称: findByCountUid
	 * @Description: 查询订单的总记录数
	 * @Author: chenD
	 * @CreateDate: Aug 23, 2017 10:23:53 AM
	 * @param uid
	 * @return Integer
	 */
	public Integer findByCountUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,uid);
		if (list != null && list.size() > 0 ) {
			return list.get(0).intValue();
		}
		return null;
	}

	/**
	 * @方法的名称: findByPageUid
	 * @Description: 查询订单每页显示的集合
	 * @Author: chenD
	 * @CreateDate: Aug 23, 2017 10:24:00 AM
	 * @param uid
	 * @param begin
	 * @param limit
	 * @return List<Order>
	 */
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		String hql = "from Order o where o.user.uid = ? order by ordertime desc";
		List<Order> list =this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid},begin,limit));
		return list;
	}

	/**
	 * @方法的名称: finyByOid
	 * @Description: 根据订单id查询订单
	 * @Author: chenD
	 * @CreateDate: Aug 23, 2017 2:39:49 PM
	 * @param oid
	 * @return Order
	 */
	public Order finyByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}
	
	
	
}
