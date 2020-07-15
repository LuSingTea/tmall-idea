package edu.fzu.tmall.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import edu.fzu.tmall.dao.CategoryDAO;
import edu.fzu.tmall.dao.CategoryDAOImpl;
import edu.fzu.tmall.dao.ProductDAO;
import edu.fzu.tmall.dao.ProductDAOHibImpl;
import edu.fzu.tmall.dao.ProductDAOImpl;
import edu.fzu.tmall.pojo.Category;
import edu.fzu.tmall.pojo.Product;
import edu.fzu.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "productService")
public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

	@Autowired
	@Resource(name = "productDAOHibImpl")
	private ProductDAO productDAO;


	@Autowired
    @Qualifier("categoryDAOImpl")
	private CategoryDAO categoryDAO;

	@Override
	public List listByPage(Page page) {
		List products= productDAO.list(page.getStart(), page.getCount());
		return products;
	}

	@Override
	public int total() {
	   int total = 0;
	   total =  productDAO.getTotal(0);
	   return total;
	}
	
	public Product get(int id){
		List list = new ArrayList<>();
		Product product = productDAO.get(id);
		//Map
		return product;
	}

	@Override
	public Boolean delete(Integer id) {
		productDAO.delete(id);
		return true;
	}

	@Override
	public Boolean update(Product product) {
		// TODO Auto-generated method stub		
		Product p=productDAO.get(product.getId());
		p.setName(product.getName());
		p.setSubTitle(product.getSubTitle());
		p.setPromotePrice(product.getPromotePrice());
		p.setOriginalPrice(product.getOriginalPrice());
		p.setCategory(product.getCategory());
		productDAO.update(p);
		return true;
	}

    @Override
    public Product get(Integer id) {
        Product product = productDAO.get(id);
        return product;
    }

}
