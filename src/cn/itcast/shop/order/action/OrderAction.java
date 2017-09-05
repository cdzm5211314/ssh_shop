/**
 * 
 */
package cn.itcast.shop.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.aspectj.weaver.AjAttribute.PrivilegedAttribute;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.cart.entity.Cart;
import cn.itcast.shop.cart.entity.CartItem;
import cn.itcast.shop.order.entity.Order;
import cn.itcast.shop.order.entity.OrderItem;
import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.user.entity.User;
import cn.itcast.shop.user.service.UserService;
import cn.itcast.shop.utils.PageBean;
import cn.itcast.shop.utils.PageHibernateCallback;
import cn.itcast.shop.utils.PaymentUtil;

/**
 * @ClassName: OrderAction
 * @Description: 订单模块表现层 
 * @Author: ChenD
 * @CreateDate: Aug 22, 2017 1:42:03 PM
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	
	private Order order =  new Order();
	public Order getModel() {
		return order;
	}
	//注入OrderService
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	//支付银行通道编号
	private String pd_Frpid;
	public void setPd_Frpid(String pd_Frpid) {
		this.pd_Frpid = pd_Frpid;
	}
	
	//接受付款成功后的响应参数
	private String r6_Order;
	private String r3_Amt;
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	/*
	 * 为订单付款的
	 */
	public String payOrder() throws IOException{
		// 1.先修改订单,保存用户名,电话和地址信息到数据库
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		// 2. 为订单付款
		String  p0_Cmd = "Buy"; //业务类型
		String  p1_MerId = "10001126856"; //商户编号
		String  p2_Order = order.getOid().toString(); //商户订单号
		String  p3_Amt = "0.01"; //付款金额
		String p4_Cur = "CNY"; //交易币种
		String  p5_Pid = ""; //商品名称 
		String p6_Pcat = ""; //商品种类 
		String  p7_Pdesc = ""; //商品描述
		String p8_Url = "http://localhost:7000/ssh_shop/order_callBack.action"; //支付成功后跳转的路径
		String   p9_SAF = "" ;//送货地址
		String  pa_MP = ""; //商户扩展信息
		String  pd_FrpId = this.pd_Frpid; //支付通道编码
		String pr_NeedResponse = "1"; //应答机制
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";//密钥
		String  hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		// 向易宝支付
		StringBuffer stringBuffer = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		stringBuffer.append("p0_Cmd=").append(p0_Cmd).append("&");
		stringBuffer.append("p1_MerId=").append(p1_MerId).append("&");
		stringBuffer.append("p2_Order=").append(p2_Order).append("&");
		stringBuffer.append("p3_Amt=").append(p3_Amt).append("&");
		stringBuffer.append("p4_Cur=").append(p4_Cur).append("&");
		stringBuffer.append("p5_Pid=").append(p5_Pid).append("&");
		stringBuffer.append("p6_Pcat=").append(p6_Pcat).append("&");
		stringBuffer.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		stringBuffer.append("p8_Url=").append(p8_Url).append("&");
		stringBuffer.append("p9_SAF=").append(p9_SAF).append("&");
		stringBuffer.append("pa_MP=").append(pa_MP).append("&");
		stringBuffer.append("pd_FrpId=").append(pd_FrpId).append("&");
		stringBuffer.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		stringBuffer.append("hmac=").append(hmac);
		//重定向到易宝
		ServletActionContext.getResponse().sendRedirect(stringBuffer.toString());
		
		return NONE;
	}
	
	/*
	 *网银支付成功跳转到
	 */
	public String callBack(){
		// 修改订单的状态:
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		// 修改订单状态为2:已经付款:
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionMessage("支付成功!订单编号为: "+r6_Order +" 付款金额为: "+r3_Amt);
		return "msg";
		
	}
	
	/*
	 * 我的订单的查询
	 */
	public String findByUid(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(),page);
		//将分页数据显示到页面上,通过值栈显示
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSUCCESS";
	}
	/*
	 * 根据订单id查询订单
	 */
	public String findByOid(){
		order = orderService.findByOid(order.getOid());
		return "findByOidSUCCESS";
	}
	
	/*
	 * 购物车页面点击"提交订单"按钮跳转到到订单页面
	 */
	public  String save(){
		// 1.保存到订单数据到数据库
		order.setOrdertime(new Date());
		order.setState(1); //1 表示未付款 , 2 表示已经付款,未发货, 3 已发货,未确认收获 , 4 交易完成
		//总计数据是购物车的总计
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			this.addActionError("亲,你还满意购物,请先去购物...");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		//设置订单中的订单项
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
		}
		//设置订单所属用户
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (user == null ) {
			this.addActionError("亲,你还没有登录,请先去登录...");
			return "login";
		}
		order.setUser(user);
		orderService.saveOrder(order);
		// 2.将订单数据显示到页面
		//通过值栈方式显示到到订单页面,应为模型驱动Order的对象本身就在值栈的栈顶,所以可以在页面直接操作
		
		//3. 清空购物车
		cart.clearCart();
		return "saveSUCCESS";
	}
	
	//确认收获:前台修改订单状态
	public String updateState(){
		//先根据id 查询订单
		Order currOrder = orderService.findByOid(order.getOid());
		//修改订单状态
		currOrder.setState(4);
		orderService.update(currOrder);
		
		return "updateStateSUCCESS";
	}
	
	
}
