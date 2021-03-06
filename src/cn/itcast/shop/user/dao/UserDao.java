/**
 * 
 */
package cn.itcast.shop.user.dao;

import java.util.List;

import javax.persistence.criteria.From;

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

	/**
	 * @方法的名称: save
	 * @Description: 保存用户注册信息到数据库
	 * @Author: chenD
	 * @CreateDate: Aug 7, 2017 9:14:52 AM
	 * @param user void
	 */
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	/**
	 * @方法的名称: findByCode
	 * @Description: 根据激活码查询用户
	 * @Author: chenD
	 * @CreateDate: Aug 7, 2017 10:54:24 AM
	 * @param code
	 * @return User
	 */
	public User findByCode(String code) {
		
		String hql = "from User where code = ?";
		List<User> list= this.getHibernateTemplate().find(hql,code);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * @方法的名称: update
	 * @Description: 激活用户修改状态码和情况code信息
	 * @Author: chenD
	 * @CreateDate: Aug 7, 2017 11:03:02 AM
	 * @param userinfo void
	 */
	public void update(User userinfo) {
		this.getHibernateTemplate().update(userinfo);
	}

	/**
	 * @方法的名称: login
	 * @Description: 用户登录操作
	 * @Author: chenD
	 * @CreateDate: Aug 7, 2017 12:22:51 PM
	 * @return User
	 */
	public User login(User user) {
		String hql = "from User where username = ? and password = ? and state = ?";
		List<User> list = this.getHibernateTemplate().find(hql, user.getUsername(),user.getPassword(),1);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	
	
}
