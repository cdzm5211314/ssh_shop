/**
 * 
 */
package cn.itcast.shop.product.service;

import java.util.List;

import org.apache.struts2.components.template.BaseTemplateEngine;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.product.dao.ProductDao;
import cn.itcast.shop.product.entity.Product;
import cn.itcast.shop.utils.PageBean;

/**
 * @ClassName: ProductService
 * @Description: 商品信息业务层
 * @Author: ChenD
 * @CreateDate: Aug 7, 2017 4:02:53 PM
 */
@Transactional
public class ProductService {

	// 注入productDao
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

	/**
	 * @方法的名称: findNew
	 * @Description: 首页查询最新的商品
	 * @Author: chenD
	 * @CreateDate: Aug 8, 2017 2:10:06 PM
	 * @return List<Product>
	 */
	public List<Product> findNew() {
		return productDao.findNew();
	}

	/**
	 * @方法的名称: findById
	 * @Description: 根据id查询商品的详细信息
	 * @Author: chenD
	 * @CreateDate: Aug 8, 2017 2:43:41 PM
	 * @param pid
	 * @return Product
	 */
	public Product findById(Integer pid) {
		return productDao.findById(pid);
	}

	/**
	 * @方法的名称: findByPageCid
	 * @Description: 根据一级分类的cid查询带分页效果的商品信息
	 * @Author: chenD
	 * @CreateDate: Aug 9, 2017 11:01:32 AM
	 * @param cid
	 * @param page
	 * @return PageBean<Product>
	 */
	public PageBean<Product> findByPageCid(Integer cid, int page) {

		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数
		pageBean.setPage(page);
		// 设置每页记录数
		int limit = 8;
		pageBean.setLimit(limit);
		// 设置总的记录数
		int totalCount = 0;
		// 数据库查询总的记录数
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		// 设置总的页数
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示的数据库集合
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Product> list = productDao.fingByPageCid(cid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * @方法的名称: findByCsid
	 * @Description: 根据二级分类id查询所有商品
	 * @Author: chenD
	 * @CreateDate: Aug 9, 2017 3:14:03 PM
	 * @param csid
	 * @param page
	 * @return PageBean<Product>
	 */
	public PageBean<Product> findByCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数
		pageBean.setPage(page);
		// 设置每页记录数
		int limit = 8;
		pageBean.setLimit(limit);
		// 设置总的记录数
		int totalCount = 0;
		// 数据库查询总的记录数
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		// 设置总的页数
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示的数据库集合
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Product> list = productDao.fingByPageCsid(csid, begin, limit);
		pageBean.setList(list);
		return pageBean;

	}

	/**
	 * @方法的名称: findByPage
	 * @Description: 后台查询商品所有信息并分页
	 * @Author: chenD
	 * @CreateDate: Sep 4, 2017 9:40:07 AM
	 * @param page
	 * @return PageBean<Product>
	 */
	public PageBean<Product> findByPage(Integer page) {
		
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页记录数
		int limit = 10;
		pageBean.setLimit(limit);
		//设置总的记录数
		int totalCount = 0;
		//数据库查询总的记录数
		totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总的页数
		int totalPage = 0;
		if (totalCount % limit  == 0) {
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页显示的数据库集合
		//从哪开始:
		int begin =  (page - 1) * limit;
		List<Product> list = productDao.fingByPage(begin,limit);
		pageBean.setList(list);
		
		return pageBean;
	}

}
