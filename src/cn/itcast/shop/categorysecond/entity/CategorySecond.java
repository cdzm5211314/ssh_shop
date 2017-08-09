/**
 * 
 */
package cn.itcast.shop.categorysecond.entity;

import java.util.HashSet;
import java.util.Set;

import cn.itcast.shop.category.entity.Category;
import cn.itcast.shop.product.entity.Product;

/**
 * @ClassName: CategorySecond
 * @Description: 二级分类实体类 
 * @Author: ChenD
 * @CreateDate: Aug 9, 2017 9:13:46 AM
 */
public class CategorySecond {
	
	private Integer csid;
	private String  csname;
	//所属一级分类,存的是一级分类对象
	private Category category;
	//商品的集合
	private Set<Product> prodocts = new HashSet<Product>();
	
	public Set<Product> getProdocts() {
		return prodocts;
	}
	public void setProdocts(Set<Product> prodocts) {
		this.prodocts = prodocts;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
