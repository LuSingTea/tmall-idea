package edu.fzu.tmall.service;

import java.util.List;

import edu.fzu.tmall.pojo.Category;
import edu.fzu.tmall.pojo.Product;
import edu.fzu.tmall.util.Page;
import org.springframework.stereotype.Service;

public interface ProductService extends BaseService {
	Boolean delete(Integer id);
	Boolean update(Product product);
	Product get(Integer id);
}
