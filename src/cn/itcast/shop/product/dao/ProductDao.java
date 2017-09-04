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
import cn.itcast.shop.utils.PageHibernateCallback;

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

		// 使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 热门商品的条件 is_hot = 1
		criteria.add(Restrictions.eq("is_hot", 1));
		// 倒序的排序输出
		criteria.addOrder(Order.desc("pdate"));
		// 执行查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);

		return list;
	}

	/**
	 * @方法的名称: findNew
	 * @Description: 首页查询最新商品信息
	 * @Author: chenD
	 * @CreateDate: Aug 8, 2017 2:10:37 PM
	 * @return List<Product>
	 */
	public List<Product> findNew() {

		// 使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 倒序的排序输出
		criteria.addOrder(Order.desc("pdate"));
		// 执行查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);

		return list;
	}

	/**
	 * @方法的名称: findById
	 * @Description: TODO
	 * @Author: chenD
	 * @CreateDate: Aug 8, 2017 2:44:25 PM
	 * @param pid
	 * @return Product
	 */
	public Product findById(Integer pid) {

		return this.getHibernateTemplate().get(Product.class, pid);
	}

	/**
	 * @方法的名称: findCountCid
	 * @Description: 根据分类的id查询商品的总数
	 * @Author: chenD
	 * @CreateDate: Aug 9, 2017 11:07:37 AM
	 * @param cid
	 * @return int
	 */
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		// 返回的数据类型的Long类型
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		// 把Long类型数据转换成int类型
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * @方法的名称: fingByPageCid
	 * @Description: 根据分类的id查询商品的集合
	 * @Author: chenD
	 * @CreateDate: Aug 9, 2017 11:19:00 AM
	 * @param cid
	 * @param begin
	 * @param limit
	 * @return List<Product>
	 */
	public List<Product> fingByPageCid(Integer cid, int begin, int limit) {
		// sql语句
		// select p.* from category c, categorysecond cs , product p where c.cid
		// 							= cs.cid and cs.csid = p.csid and c.cid = 1;
		// hql语句
		// select p from Category c, CategorySecond cs , Product p where c.cid =
		// 				cs.category.cid and cs.csid = p.categorysecond.csid and c.cid = ?
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		// 分页另一种查询,定义一个类继承HibernateCallback
		List<Product> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Product>(hql, new Object[] { cid }, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * @方法的名称: findCountCsid
	 * @Description: 根据二级分类id查询总记录数
	 * @Author: chenD
	 * @CreateDate: Aug 9, 2017 3:16:26 PM
	 * @param csid
	 * @return int
	 */
	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ? ";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		// 把Long类型数据转换成int类型
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * @方法的名称: fingByPageCsid
	 * @Description: 根据二级分类id分页查询商品
	 * @Author: chenD
	 * @CreateDate: Aug 9, 2017 3:17:02 PM
	 * @param csid
	 * @param begin
	 * @param limit
	 * @return List<Product>
	 */
	public List<Product> fingByPageCsid(Integer csid, int begin, int limit) {
		String  hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * @方法的名称: findCount
	 * @Description: 查询商品的总个数
	 * @Author: chenD
	 * @CreateDate: Sep 4, 2017 9:47:18 AM
	 * @return int
	 */
	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * @方法的名称: fingByPage
	 * @Description: 查询商品带分页返回
	 * @Author: chenD
	 * @CreateDate: Sep 4, 2017 9:47:25 AM
	 * @param begin
	 * @param limit
	 * @return List<Product>
	 */
	public List<Product> fingByPage(int begin, int limit) {
		String hql = "from Product order by pdate desc";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * @方法的名称: save
	 * @Description: 保存商品数据到数据库
	 * @Author: chenD
	 * @CreateDate: 2017年9月4日 下午1:49:27
	 * @param product void
	 */
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	/**
	 * @方法的名称: delete
	 * @Description: 删除商品数据
	 * @Author: chenD
	 * @CreateDate: 2017年9月4日 下午2:18:15
	 * @param product void
	 */
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}

	/**
	 * @方法的名称: update
	 * @Description: 修稿商品数据
	 * @Author: chenD
	 * @CreateDate: 2017年9月4日 下午3:15:21
	 * @param product void
	 */
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

}
