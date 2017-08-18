/**
 * 
 */
package cn.itcast.shop.cart.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: Cart
 * @Description: 购物车实体类
 * @Author: ChenD
 * @CreateDate: Aug 9, 2017 3:53:10 PM
 */
public class Cart implements Serializable {

	// 购物项集合:key是商品的pid,value是购物项,有序集合
	private Map<Integer, CartItem> map = new LinkedHashMap<>();
	
	//Cart对象中存在一个叫cartItems的属性
	public  Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	private double total; // 购物总计
	public double getTotal() {
		return total;
	}

	// 购物车功能:
	// 1. 将购物项添加到购物车
	public void addCart(CartItem cartItem) {
		// 贩毒案购物车中是否存在该购物项
		// 如果存在 数量增加 总结+=购物项小计
		// 如果不存在 向map中添加购物项 总计+=购物项小计
		// 获得商品pid
		Integer pid = cartItem.getProduct().getPid();
		// 判断购物项是否存在
		if (map.containsKey(pid)) {
			// 存在
			// 获取购物车中原来的购物项
			CartItem _cartItem = map.get(pid);
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
		} else {
			// 不存在
			map.put(pid, cartItem);
		}
		// 设置总计的值
		total += cartItem.getSubtotal();
	}

	// 2.从购物车中移除购物项
	public void removeCart(Integer pid) {
		// 将购物项移除购物车
		CartItem cartItem = map.remove(pid);
		// 总计 = 总计 - 移除的购物项
		total -= cartItem.getSubtotal();
	}

	// 3.清空购物车
	public void clearCart() {
		// 将所有购物项清空
		map.clear();
		// 把总计设置为0
		total = 0;
	}

}
