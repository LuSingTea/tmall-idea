package edu.fzu.tmall.dao;

import edu.fzu.tmall.pojo.Category;
import edu.fzu.tmall.pojo.Product;
import edu.fzu.tmall.util.HibernateUtil;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 使用hibernate完成功能
 */
@Repository(value = "productDAOHibImpl")
public class ProductDAOHibImpl implements ProductDAO {
    @Autowired
    private SessionFactory sessionFactory;

    // 总记录数
    @Override
    public int getTotal(int cid) {
        int total = 0;
        try {
            Session session = HibernateUtil.getSession();
            String hql = " SELECT count(p) FROM Product AS p where p.delFlag=0 ";
            Query query = session.createQuery(hql);
            total = ((Long) query.uniqueResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        System.out.println("总的个数为:" + total);
        return total;
    }

    @Override
    public void add(Product bean) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(bean);
        transaction.commit();
    }

    @Override
    public void update(Product bean) {
        Session session = HibernateUtil.getSession();
        // 增删改需要开启事务
        Transaction transaction = session.beginTransaction();
        session.update(bean);
        transaction.commit(); // 提交
        HibernateUtil.closeSession();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.get(Product.class, id);
        product.setDelFlag(1);  // 逻辑删除
        transaction.commit();
        HibernateUtil.closeSession();

    }

    /**
     * 根据id查找商品
     *
     * @param id
     * @return
     */
    @Override
    public Product get(int id) {
        Session session = HibernateUtil.getSession();
        Product product = (Product) session.get(Product.class, id);
        HibernateUtil.closeSession();
        return product;
    }

    @Override
    public List<Product> list(int cid) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Product p where p.category=" + cid);
        List<Product> list = query.list();
        return list;
    }

    @Override
    public List<Product> list(int cid, int start, int count) {
        return null;
    }

    @Override
    public List<Product> list() {
        // TODO Auto-generated method stub
        return null;
    }

    // 分页查询
    @SuppressWarnings("unchecked")
    @Override
    public List<Product> list(int start, int count) {
        List<Product> products = null;
        try {
            Session session = HibernateUtil.getSession();
            // 查询方式一: hql查询

            String hql = " SELECT p FROM Product AS p where p.delFlag=0 ";
            Query query = session.createQuery(hql); // 支持分页查询
            products = query.setFirstResult(start).setMaxResults(count).list();


            // 查询方式二: sql查询
            /*
             * String sql = "select * from product where delFlag=:delFlag";
             * products =session.createSQLQuery(sql)
             * 					.addEntity(Product.class)
             * 					.setInteger("delFlag", 0)
             * 					.setFirstResult(start)
             * 					.setMaxResults(count).list();
             */


            // 查询方式三: 条件查询
            /*
             * products =session.createCriteria(Product.class)
             * 					.add(Restrictions.eq("delFlag",0))
             * 					.setFirstResult(start)
             * 					.setMaxResults(count)
             * 					.addOrder(Order.desc("id"))
             * 					.list();
             */

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return products;
    }

    @Override
    public void fill(List<Category> categories) {
        // TODO Auto-generated method stub

    }

    @Override
    public void fill(Category c) {
        // TODO Auto-generated method stub

    }

    @Override
    public void fillByRow(List<Category> categories) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setFirstProductImage(Product p) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Product> search(String keyword, int start, int count) {
        // TODO Auto-generated method stub
        return null;
    }

}