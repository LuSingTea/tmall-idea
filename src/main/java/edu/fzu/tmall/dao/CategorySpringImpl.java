package edu.fzu.tmall.dao;

import edu.fzu.tmall.pojo.Category;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "categorySpringImpl")
public class CategorySpringImpl implements CategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int getTotal() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Category.class);
        List list = criteria.list();
        int size = list.size();
        return size;
    }

    @Override
    public void add(Category bean) {
        Session session = sessionFactory.getCurrentSession();
        session.save(bean);
    }

    @Override
    public void update(Category bean) {
        Session session = sessionFactory.getCurrentSession();
        session.update(bean);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Category category = new Category();
        category.setId(id);
        session.delete(category);
        transaction.commit();

    }

    @Override
    public Category get(int id) {
        Session session = sessionFactory.getCurrentSession();
        Category category = (Category) session.get("Category", id);
        return category;
    }

    @Override
    public List<Category> list() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Category");

        List<Category> list = query.list();
        return list;
    }

    @Override
    public List<Category> list(int start, int count) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Category");
        query.setFirstResult(start).setMaxResults(count);
        List<Category> list = query.list();
        return list;
    }
}
