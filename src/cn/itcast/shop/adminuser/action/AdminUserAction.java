/**
 * 
 */
package cn.itcast.shop.adminuser.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.adminuser.entity.AdminUser;
import cn.itcast.shop.adminuser.service.AdminUserService;

/**
 * @ClassName: AdminUserAction
 * @Description: 后台管理员用户表现层
 * @Author: ChenD
 * @CreateDate: Aug 31, 2017 2:07:22 PM
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{
	
	private AdminUser adminUser = new AdminUser();
	public AdminUser getModel() {
		return adminUser;
	}
	
	//注入service
	private AdminUserService adminUserService;
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	

	
	
	
	
}
