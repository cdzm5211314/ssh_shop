/**
 * 
 */
package cn.itcast.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.adminuser.entity.AdminUser;

/**
 * @ClassName: AdminUserDao
 * @Description: 后台管理员用户数据访问层 
 * @Author: ChenD
 * @CreateDate: Aug 31, 2017 2:10:01 PM
 */
public class AdminUserDao extends HibernateDaoSupport {

	/**
	 * @方法的名称: findByAdminUserAndPassword
	 * @Description: 查询管理员的用户名与密码是否正确
	 * @Author: chenD
	 * @CreateDate: Aug 31, 2017 2:30:09 PM
	 * @param adminUser
	 * @return AdminUser
	 */
	public AdminUser findByAdminUserAndPassword(AdminUser adminUser) {
		String sql = "from AdminUser where username =? and password = ?";
		List<AdminUser> list = this.getHibernateTemplate().find(sql, adminUser.getUsername(),adminUser.getPassword());
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
