/**
 * 
 */
package cn.itcast.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.categorysecond.entity.CategorySecond;
import cn.itcast.shop.utils.PageHibernateCallback;

/**
 * @ClassName: CategorySecondDao
 * @Description: 二级分类数据层 
 * @Author: ChenD
 * @CreateDate: Sep 1, 2017 12:50:37 PM
 */
public class CategorySecondDao extends HibernateDaoSupport {

	/**
	 * @方法的名称: findCount
	 * @Description: 统计二级分类的总个数
	 * @Author: chenD
	 * @CreateDate: Sep 1, 2017 1:21:57 PM
	 * @return int
	 */
	public int findCount() {
		String  hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * @方法的名称: findByPage
	 * @Description: 分页查询二级分类
	 * @Author: chenD
	 * @CreateDate: Sep 1, 2017 1:22:36 PM
	 * @param begin
	 * @param limit
	 * @return List<CategorySecond>
	 */
	public List<CategorySecond> findByPage(int begin, int limit) {
		String hql = "from CategorySecond order by csid desc";
		List<CategorySecond> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<CategorySecond>(hql, null, begin,
						limit));
		System.err.println(list);
		return list;
	}

	/**
	 * @方法的名称: save
	 * @Description: 添加二级分类
	 * @Author: chenD
	 * @CreateDate: Sep 1, 2017 3:56:46 PM
	 * @param categorySecond void
	 */
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	/**
	 * @方法的名称: delete
	 * @Description: 删除二级分类
	 * @Author: chenD
	 * @CreateDate: Sep 1, 2017 4:12:28 PM
	 * @param categorySecond void
	 */
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	/**
	 * @方法的名称: findByCsid
	 * @Description: 根据csid查询二级分类
	 * @Author: chenD
	 * @CreateDate: Sep 1, 2017 4:15:43 PM
	 * @param csid
	 * @return CategorySecond
	 */
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	/**
	 * @方法的名称: update
	 * @Description: 修改二级分类
	 * @Author: chenD
	 * @CreateDate: Sep 1, 2017 4:52:55 PM
	 * @param categorySecond void
	 */
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}
	
	
}
