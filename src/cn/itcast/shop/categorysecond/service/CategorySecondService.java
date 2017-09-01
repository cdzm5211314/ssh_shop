/**
 * 
 */
package cn.itcast.shop.categorysecond.service;

import java.util.List;

import cn.itcast.shop.categorysecond.dao.CategorySecondDao;
import cn.itcast.shop.categorysecond.entity.CategorySecond;
import cn.itcast.shop.utils.PageBean;

/**
 * @ClassName: CategorySecondService
 * @Description: 二级分类业务层 
 * @Author: ChenD
 * @CreateDate: Sep 1, 2017 12:49:48 PM
 */
public class CategorySecondService {
	
	//注入CategorySecondDao
	private CategorySecondDao categorySecondDao;
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	/**
	 * @方法的名称: findByPage
	 * @Description: 分页查询所有二级分类
	 * @Author: chenD
	 * @CreateDate: Sep 1, 2017 1:10:15 PM
	 * @param page
	 * @return PageBean<CategorySecond>
	 */
	public PageBean<CategorySecond> findByPage(Integer page) {
		
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示记录数
		int limit = 10;
		pageBean.setTotalPage(limit);
		//设置总的记录数
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		
		//设置总页数
		int totalPage = 0; 
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		//设置每页显示数据的集合
		int begin = ( page - 1 ) * limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
 		pageBean.setList(list);
		
		return pageBean;
	}
	/**
	 * @方法的名称: save
	 * @Description: 添加二级分类
	 * @Author: chenD
	 * @CreateDate: Sep 1, 2017 3:56:13 PM
	 * @param categorySecond void
	 */
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}
	
	
	
	
}
