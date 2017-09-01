/**
 * 
 */
package cn.itcast.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.category.entity.Category;

/**
 * @ClassName: CategoryDao
 * @Description: 一级分类持久层 
 * @Author: ChenD
 * @CreateDate: Aug 7, 2017 3:06:09 PM
 */
public class CategoryDao extends HibernateDaoSupport {

	/**
	 * @方法的名称: findAll
	 * @Description: 查询所有一级分类
	 * @Author: chenD
	 * @CreateDate: Aug 7, 2017 3:25:05 PM
	 * @return List<Category>
	 */
	public List<Category> findAll() {
		List<Category> list = this.getHibernateTemplate().find("from Category");
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * @方法的名称: save
	 * @Description: 后台保存一级分类数据
	 * @Author: chenD
	 * @CreateDate: Sep 1, 2017 10:56:13 AM
	 * @param category void
	 */
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	/**
	 * @方法的名称: findByCid
	 * @Description: 根据cid查询一级分类
	 * @Author: chenD
	 * @CreateDate: Sep 1, 2017 11:20:49 AM
	 * @param cid
	 * @return Category
	 */
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	/**
	 * @方法的名称: delete
	 * @Description: 删除一级分类数据
	 * @Author: chenD
	 * @CreateDate: Sep 1, 2017 12:14:23 PM
	 * @param category void
	 */
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	/**
	 * @方法的名称: update
	 * @Description: 修改一级分类
	 * @Author: chenD
	 * @CreateDate: Sep 1, 2017 12:22:06 PM
	 * @param category void
	 */
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}
	
	
}
