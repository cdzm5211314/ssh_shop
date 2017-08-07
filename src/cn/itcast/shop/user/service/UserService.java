/**
 * 
 */
package cn.itcast.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.user.dao.UserDao;
import cn.itcast.shop.user.entity.User;
import cn.itcast.shop.utils.UUIDUtils;

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
	/**
	 * @方法的名称: saveUser
	 * @Description: 注册用户保存信息
	 * @Author: chenD
	 * @CreateDate: Aug 7, 2017 9:07:10 AM
	 * @param user void
	 */
	public void saveUser(User user) {
		
		//设置用户信息的  状态码和code信息
		user.setState(0); //0: 表示未激活 1:表示已激活
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		//保存用户信息到数据库
		userDao.save(user);
		
	}

	
	
}
