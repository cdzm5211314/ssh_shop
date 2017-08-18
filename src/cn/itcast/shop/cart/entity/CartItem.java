/**
 * 
 */
package cn.itcast.shop.cart.entity;

import cn.itcast.shop.product.entity.Product;

/**
 * @ClassName: CartItem
 * @Description: 购物项实体类 
 * @Author: ChenD
 * @CreateDate: Aug 9, 2017 3:52:43 PM
 */
public class CartItem {

	private Product product;	//购物项的中商品
	private int count;			//购买某种商品的数量
	private  double subtotal; 	//购买某种商品的小计
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	//小计通过自动计算得来
	public double getSubtotal() {
		return count * product.getShop_price();
	}
	/*public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}*/
	
}
