package cn.itcast.shop.index.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @ClassName: IndexAction
 * @Description: 首页访问的Action 
 * @Author: ChenD
 * @CreateDate: Aug 3, 2017 10:13:35 AM
 */
public class IndexAction extends ActionSupport{
	
	/**
	 * 访问首页的方法
	 */
	public String execute(){
		
		return  "indexSUCCESS";
	}  
	
}
