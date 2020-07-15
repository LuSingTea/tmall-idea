package edu.fzu.tmall.dao;

import edu.fzu.tmall.pojo.Category;
import edu.fzu.tmall.pojo.Product;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "productDaoSpringImpl")
public class ProductDaoSpringImpl implements ProductDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public int getTotal(int cid) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from Product where cid="+cid);
        Integer integer = (Integer) query.uniqueResult();
        return integer;
    }

    @Override
    public void add(Product bean) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(bean);
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }
    }


    @Override
    public void update(Product bean) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(bean);
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Product product = new Product();
            product.setId(id);
            session.delete(product);
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public Product get(int id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);
        return product;
    }

    @Override
    public List<Product> list(int cid) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Product where cid="+cid);
        List<Product> list = query.list();
        return list;
    }

    @Override
    public List<Product> list(int cid, int start, int count) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Product where cid="+cid);
        query.setFirstResult(start).setMaxResults(count);
        List<Product> list = query.list();
        return list;
    }

    @Override
    public List<Product> list() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Product");
        List<Product> list = query.list();
        return list;
    }

    @Override
    public List<Product> list(int start, int count) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Product");
        List<Product> list = query.list();
        return list;
    }

    @Override
    public void fill(List<Category> categories) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            session.save(category);
            if (i % 10 == 0) {
                session.flush();
                session.clear();
            }
        }
        transaction.commit();
    }

    @Override
    public void fill(Category c) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(c);
        transaction.commit();
    }

    @Override
    public void fillByRow(List<Category> categories) {

    }

    @Override
    public void setFirstProductImage(Product p) {

    }

    @Override
    public List<Product> search(String keyword, int start, int count) {
        return null;
    }
}
