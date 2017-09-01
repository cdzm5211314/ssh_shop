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
	
	
	
	
}
