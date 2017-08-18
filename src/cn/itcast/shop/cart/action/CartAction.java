/**
 * 
 */
package cn.itcast.shop.cart.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @ClassName: CartAction
 * @Description: 购物车表现层 
 * @Author: ChenD
 * @CreateDate: Aug 18, 2017 12:58:00 PM
 */
public class CartAction extends ActionSupport {


	/*
	 * 购物项添加到购物车中
	 */
	public String addCart(){
		
		return "addCartSUCCESS";
	}
	
	
}
