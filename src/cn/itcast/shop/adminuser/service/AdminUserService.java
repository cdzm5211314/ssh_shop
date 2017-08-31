/**
 * 
 */
package cn.itcast.shop.adminuser.service;

import cn.itcast.shop.adminuser.dao.AdminUserDao;

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
	
	
}
