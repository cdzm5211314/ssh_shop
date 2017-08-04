/**
 * 
 */
package cn.itcast.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.user.entity.User;

/**
 * @ClassName: UserDao
 * @Description: 用户模块数据层 
 * @Author: ChenD
 * @CreateDate: Aug 4, 2017 1:12:57 PM
 */
public class UserDao extends HibernateDaoSupport{

	/**
	 * @方法的名称: findByUsername
	 * @Description: 查询用户名是否存在
	 * @Author: chenD
	 * @CreateDate: Aug 4, 2017 1:39:29 PM
	 * @param username
	 * @return User
	 */
	public User findByUsername(String username) {
		
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql,username);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	
	
}
