package edu.fzu.tmall.dao;

import java.util.List;

import edu.fzu.tmall.pojo.Category;

public interface CategoryDAO {

	int getTotal();

	void add(Category bean);

	void update(Category bean);

	void delete(int id);

	Category get(int id);

	List<Category> list();

	List<Category> list(int start, int count);

}