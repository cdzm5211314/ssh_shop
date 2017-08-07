/**
 * 
 */
package cn.itcast.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.product.entity.Product;

/**
 * @ClassName: ProductDao
 * @Description: 商品信息持久层 
 * @Author: ChenD
 * @CreateDate: Aug 7, 2017 4:03:26 PM
 */
public class ProductDao extends HibernateDaoSupport {

	/**
	 * @方法的名称: findHot
	 * @Description: 首页热门商品查询
	 * @Author: chenD
	 * @CreateDate: Aug 7, 2017 4:21:56 PM
	 * @return List<Product>
	 */
	public List<Product> findHot() {
		
		//使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);	
		//热门商品的条件 is_hot = 1
		criteria.add(Restrictions.eq("is_hot", 1));
		//倒序的排序输出
		criteria.addOrder(Order.desc("pdate"));
		//执行查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10);
		
		return list;
	}

	
	
}
