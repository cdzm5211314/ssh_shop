/**
 * 
 */
package cn.itcast.shop.adminuser.service;

import cn.itcast.shop.adminuser.dao.AdminUserDao;
import cn.itcast.shop.adminuser.entity.AdminUser;

/**
 * @ClassName: AdminUserService
 * @Description: 后台管理员用户业务层 
 * @Author: ChenD
 * @CreateDate: Aug 31, 2017 2:09:31 PM
 */
public class AdminUserService {

	//注入dao
	private AdminUserDao adminUserDao;
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
	/**
	 * @方法的名称: login
	 * @Description: 管理员登录操作
	 * @Author: chenD
	 * @CreateDate: Aug 31, 2017 2:29:28 PM
	 * @param adminUser
	 * @return AdminUser
	 */
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.findByAdminUserAndPassword(adminUser);
	}
	
	
}
