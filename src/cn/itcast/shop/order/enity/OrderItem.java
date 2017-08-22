/**
 * 
 */
package cn.itcast.shop.order.enity;

import cn.itcast.shop.product.entity.Product;

/**
 * @ClassName: OrderItem
 * @Description: 订单项实体类 
 * @Author: ChenD
 * @CreateDate: Aug 22, 2017 1:59:50 PM
 */
public class OrderItem {

	private Integer itemid;
	private Integer count;
	private Double subtotal;
	
	private Product product;
	private Order order;
	
	
	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
}
