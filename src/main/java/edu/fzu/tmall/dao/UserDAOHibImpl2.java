package edu.fzu.tmall.dao;

import edu.fzu.tmall.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "userDAOHibImpl2")
public class UserDAOHibImpl2 implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int getTotal() {
        int total = 0;
        String hql = "SELECT count(u) FROM User AS u ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        total = ((Long) query.uniqueResult()).intValue();
        return total;
    }

    @Override
    public void add(User user) {
        // TODO Auto-generated method stub
//		Session session=HibernateUtil.getSession();
//	    session.beginTransaction();                //开启事务
//	    session.save(user); //执行完user变成 持久化状态                  //执行数据库添加操作
//	    //user.setPassword("654321");
//	    session.getTransaction().commit();
//	    HibernateUtil.closeSession();
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public void update(User user) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        session.update(user);

    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
//		Session session=HibernateUtil.getSession();
//	    session.beginTransaction();
//	    //开启事务
//	    User user=(User)session.get(User.class, id);
//	    session.delete(user);//执行完user变成 持久化状态                  //执行数据库添加操作
//	    //user.setPassword("654321");
//	    session.getTransaction().commit();
//	    HibernateUtil.closeSession();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setId(id);
        session.delete(user);
        transaction.commit();
    }

    @Override
    public User get(int id) {
//		Session session = HibernateUtil.getSession();
//	    User user=(User)session.get(User.class, id);
//		user.setPassword("654321");
//	    HibernateUtil.closeSession();
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        return user;
    }

    @Override
    public List<User> list() {
        // TODO Auto-generated method stub
        List<User> users = null;
        Session session = sessionFactory.getCurrentSession();
        String hql = "from User";
        Query query = session.createQuery(hql);
        users = query.list();
        return users;
    }

    @Override
    public List<User> list(int start, int count) {
        List<User> users = null;
//		try {
//			Session session = HibernateUtil.getSession();
//			String hql = "SELECT u FROM User AS u ";
//			Query query = session.createQuery(hql);
//			query.setFirstResult(start).setMaxResults(count);
//			users = query.list();
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: handle exception
//		} finally {
//			HibernateUtil.closeSession();
//		}
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT u FROM User AS u ";
        Query query = session.createQuery(hql);
        query.setFirstResult(start).setMaxResults(count);
        users = query.list();
        return users;
    }

    @Override
    public boolean isExist(String name) {
        // TODO Auto-generated method
        String sql = "from User where name = " + name;
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        User user = (User) query.uniqueResult();
        return user != null;
    }

    @Override
    public User get(String name) {
        // TODO Auto-generated method stub
        String sql = "from User where name = " + name;
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        User result = (User) query.uniqueResult();
        return result;
    }

    @Override
    public User get(String name, String password) {
        // TODO Auto-generated method stub
        String sql = String.format("from User where name='%s' and password='%s'", name, password);
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        User result = (User) query.uniqueResult();
        System.out.println(result);
        return result;
    }

}