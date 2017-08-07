/**
 * 
 */
package cn.itcast.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.product.dao.ProductDao;
import cn.itcast.shop.product.entity.Product;

/**
 * @ClassName: ProductService
 * @Description: 商品信息业务层 
 * @Author: ChenD
 * @CreateDate: Aug 7, 2017 4:02:53 PM
 */
@Transactional
public class ProductService {

	//注入productDao
	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	/**
	 * @方法的名称: findHot
	 * @Description: 首页上热门商品查询
	 * @Author: chenD
	 * @CreateDate: Aug 7, 2017 4:21:23 PM
	 * @return List<Product>
	 */
	public List<Product> findHot() {
		return productDao.findHot();
	}
	
	
}
