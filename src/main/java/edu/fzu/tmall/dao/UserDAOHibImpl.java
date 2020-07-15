package edu.fzu.tmall.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import edu.fzu.tmall.pojo.User;
import edu.fzu.tmall.util.HibernateUtil;

@Repository(value = "userDAOHibImpl")
public class UserDAOHibImpl implements UserDAO {

	@Override
	public int getTotal() {
		int total=0;
		try {
			Session session = HibernateUtil.getSession();
			String hql = "SELECT count(u) FROM User AS u ";
			Query query = session.createQuery(hql);
			total = ((Long)query.uniqueResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			HibernateUtil.closeSession();
		}
		return total;
	}

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSession();
	    session.beginTransaction();                //开启事务
	    session.save(user);//执行完user变成 持久化状态                  //执行数据库添加操作
	    //user.setPassword("654321");
	    session.getTransaction().commit();
	    HibernateUtil.closeSession();
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSession();
	    session.beginTransaction(); 
	    //开启事务
	    User user=(User)session.get(User.class, id);
	    session.delete(user);//执行完user变成 持久化状态                  //执行数据库添加操作
	    //user.setPassword("654321");
	    session.getTransaction().commit();
	    HibernateUtil.closeSession();
	}

	@Override
	public User get(int id) {
		Session session=HibernateUtil.getSession();
	    User user=(User)session.get(User.class, id);
		user.setPassword("654321");
	    HibernateUtil.closeSession();
		return user;
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		List<User> users = null;
		try {
			Session session = HibernateUtil.getSession();
			String hql = "SELECT u FROM User AS u ";
			Query query = session.createQuery(hql);
			users = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			HibernateUtil.closeSession();
		}
		return users;
	}

	@Override
	public List<User> list(int start, int count) {
		List<User> users = null;
		try {
			Session session = HibernateUtil.getSession();
			String hql = "SELECT u FROM User AS u ";
			Query query = session.createQuery(hql);
			query.setFirstResult(start).setMaxResults(count);
			users = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			HibernateUtil.closeSession();
		}
		return users;
	}

	@Override
	public boolean isExist(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User get(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
