/**
 * 
 */
package cn.itcast.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.RequestingUserName;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.categorysecond.entity.CategorySecond;
import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.product.entity.Product;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.utils.PageBean;

/**
 * @ClassName: AdminProductAction
 * @Description: 后台商品管理的表现层 
 * @Author: ChenD
 * @CreateDate: Sep 4, 2017 9:24:58 AM
 */
public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {

	private Product product = new Product();
	public Product getModel() {
		return product;
	}
	
	//注入service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	//接受finAll方法传递过来的参数page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	//文件上传所需要参数
	private File upload;//上传的文件
	private String uploadFileName;//接受上传文件的文件名称
	private String uploadContextType;//接受上传文件的MIME的类型
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}
	//查询所有商品信息并分页显示
	public String findAll(){
		//调用service
		PageBean<Product> pageBean = productService.findByPage(page);
		//将数据传递到页面上
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllSUCCESS";
	}
	//点击"添加"按钮跳转到添加商品页面
	public String addPage(){
		//需要查询所有二级分类集合
		List<CategorySecond> csList = categorySecondService.findAll();
		//保存数据到值栈
		ActionContext.getContext().getValueStack().set("csList", csList);
		
		return "addPageSUCCESS";
	}
	
	//保存商品的方法
	public String save() throws IOException{
		product.setPdate(new Date());
		//完成文件上传
		if (upload != null) {
			//获得文件上传的磁盘的绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//创建一个文件
			File diskFile = new File(realPath+"//"+uploadFileName);
			//文件上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/"+uploadFileName);
		}
		productService.save(product);
		return "saveSUCCESS";
	}
	
	//删除商品操作
	public String delete(){
		
		//先根据id查询商品,在删除
		product = productService.findById(product.getPid());
		//也需要删除上传图片
		String path = product.getImage();
		if (path != null) {
			String realPath = ServletActionContext.getServletContext().getRealPath("/"+path);
			File file = new File(realPath);
			file.delete();
		}
		productService.delete(product);
		return "deleteSUCCESS";
	}
	

}
