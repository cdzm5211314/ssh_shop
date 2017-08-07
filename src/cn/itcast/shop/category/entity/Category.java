/**
 * 
 */
package cn.itcast.shop.category.entity;

/**
 * @ClassName: Category
 * @Description: 一级分类实体类 
 * @Author: ChenD
 * @CreateDate: Aug 7, 2017 3:04:57 PM
 */
public class Category {
	
	private Integer cid;
	private String cname;
	
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
