/**
 * 
 */
package cn.itcast.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.user.dao.UserDao;
import cn.itcast.shop.user.entity.User;

/**
 * @ClassName: UserService
 * @Description: 用户模块业务层 
 * @Author: ChenD
 * @CreateDate: Aug 4, 2017 11:20:05 AM
 */
@Transactional
public class UserService {
	
	//注入UserDao
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	/**
	 * @方法的名称: findByUsername
	 * @Description: 查询用户名是否存在
	 * @Author: chenD
	 * @CreateDate: Aug 4, 2017 1:38:38 PM
	 * @param username
	 * @return User
	 */
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	
	
}
