/**
 * 
 */
package cn.itcast.shop.user.action;

import java.io.IOException;

import javax.print.attribute.standard.RequestingUserName;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.org.apache.regexp.internal.REDebugCompiler;

import cn.itcast.shop.user.entity.User;
import cn.itcast.shop.user.service.UserService;

/**
 * @ClassName: UserAction
 * @Description: TODO 
 * @Author: ChenD
 * @CreateDate: Aug 3, 2017 3:11:48 PM
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	//模型驱动
	private User user = new User();
	public User getModel() {
		return user;
	}
	//注入UserService
	public UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	//属性驱动
	private String checkCode;
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	/*
	 * 用户的退出操作
	 */
	public  String quit(){
		
		//销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		
		return "quitSUCCESS";
	}
	/*
	 * 用户登录操作
	 */
	public  String login(){
		
		User userinfo = userService.login(user);
		//判断用户
		if (userinfo == null) {
			//登录失败
			this.addActionError("登录失败:用户名或密码错误或用户未激活");
			return LOGIN;
		}else {
			//登录成功
			//把用户保存到session中并完成跳转
			ServletActionContext.getRequest().getSession().setAttribute("user", userinfo);
			return "loginSUCCESS";
		}
	}
	
	
	/*
	 * 注册保存用户信息
	 */
	public String save(){
		
		//从session中获取验证码随机值(CheckImgAction.java)
		String checkCode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkCode");
		//判断session中的验证是否与注册页面的验证码值是否一致
		if (!checkCode.equalsIgnoreCase(checkCode1) ) {
			this.addActionError("验证码输入错误...");
			return "checkCodeFail";
		}
		userService.saveUser(user);
		this.addActionMessage("注册成功,请去邮箱激活...");
		return "msg";
	}

	/**
	 * ajax异步操作校验用户名是否存在
	 * @throws IOException 
	 */
	public String findByUsername() throws IOException{
		
		User userinfo = userService.findByUsername(user.getUsername());
		//获得response对象,对页面进行输出
		HttpServletResponse response = ServletActionContext.getResponse();
		//对页面输出需要中文
		response.setContentType("text/html;charset=UTF-8");
		//判断用户名是否存在
		if ( userinfo != null) {
			//查询到,表示用户名存在
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		}else {
			//没有查询到,表示用户名不存在
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}

	/**
	 * 跳转到注册页面
	 */
	public String registPage(){
		
		return "registPageSUCCESS";
	}
	/*
	 * 跳转到登录页面
	 */
	public String loginPage(){
		
		return "loginPageSUCCESS";
	}
	
	
	/*
	 * 激活注册的用户
	 */
	public  String active(){
	
		//根据激活码查询用户信息
		User userinfo = userService.findByUsername(user.getCode());
		if (userinfo == null) {
			this.addActionMessage("用户激活失败....");
		}else {
			//修改用户状态,清空激活码信息
			userinfo.setState(1);
			userinfo.setCode(null);
			
			userService.update(userinfo);
			this.addActionMessage("激活成功,请去登录...");
		}
		return "msg";
	}
	
	
	
}
