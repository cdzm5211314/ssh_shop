/**
 * 
 */
package cn.itcast.shop.adminuser.entity;

/**
 * @ClassName: AdminUser
 * @Description: 后台管理员用户实体类 
 * @Author: ChenD
 * @CreateDate: Aug 31, 2017 2:00:37 PM
 */
public class AdminUser {
	
	private Integer uid;
	private String username;
	private String  password;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
