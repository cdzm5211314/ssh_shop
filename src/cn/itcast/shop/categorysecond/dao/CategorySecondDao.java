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
	
	
}