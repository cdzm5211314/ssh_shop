/**
 * 
 */
package cn.itcast.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.itcast.shop.adminuser.entity.AdminUser;

/**
 * 对没有登录的用户不可以访问
 * @ClassName: PrivilegeInterceptor
 * @Description: 后台权限拦截器 
 * @Author: ChenD
 * @CreateDate: Sep 5, 2017 1:23:19 PM
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {
	
	//执行的拦截方法
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//判断session中是否保存了后台用户的信息
		AdminUser ifAdmin = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("adminUser");
		if (ifAdmin == null) {
			//没有登陆
			ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("你还没有登录,无权访问...");
			return "longFail";
		}else {
			//已经登陆
			return actionInvocation.invoke();
		}
	}

}
