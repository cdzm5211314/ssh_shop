/**
 * 
 */
package cn.itcast.shop.category.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import cn.itcast.shop.categorysecond.entity.CategorySecond;

/**
 * @ClassName: Category
 * @Description: 一级分类实体类 
 * @Author: ChenD
 * @CreateDate: Aug 7, 2017 3:04:57 PM
 */
public class Category implements Serializable{
	
	private Integer cid;
	private String cname;
	//包含的二级分类,用set集合表示
	private Set<CategorySecond> categorySeconds =  new HashSet<CategorySecond>();
	
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	
}
