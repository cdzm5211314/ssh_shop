/**
 * 
 */
package cn.itcast.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.category.dao.CategoryDao;
import cn.itcast.shop.category.entity.Category;

/**
 * @ClassName: CategoryService
 * @Description: 一级分类业务层 
 * @Author: ChenD
 * @CreateDate: Aug 7, 2017 3:05:44 PM
 */
@Transactional
public class CategoryService {
	
	//注入dao	
	private CategoryDao categoryDao;
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	/**
	 * @方法的名称: findAll
	 * @Description: 查所有一级分类数据
	 * @Author: chenD
	 * @CreateDate: Aug 7, 2017 3:24:25 PM
	 * @return List<Category>
	 */
	public List<Category> findAll() {
		return categoryDao.findAll();
	}
	/**
	 * @方法的名称: save
	 * @Description: 后台保存一级分类
	 * @Author: chenD
	 * @CreateDate: Sep 1, 2017 10:55:32 AM
	 * @param category void
	 */
	public void save(Category category) {
		categoryDao.save(category);
	}
	
	
	
}
