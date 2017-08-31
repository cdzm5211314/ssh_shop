/**
 * 
 */
package cn.itcast.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

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
	
	//管理员登录操作
	public String login(){
		//调用业务层登录
		AdminUser loginAdminUser = adminUserService.login(adminUser);
		if (loginAdminUser == null) {
			//登录失败
			this.addActionError("用户名称或密码错误...");
			return "loginFail";
		}
		//登录成功
		ServletActionContext.getRequest().getSession().setAttribute("adminUser", loginAdminUser);
		return "loginSUCCESS";
	}
	
	
	
	
}
