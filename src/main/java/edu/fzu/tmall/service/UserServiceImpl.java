package edu.fzu.tmall.service;

import edu.fzu.tmall.dao.UserDAO;
import edu.fzu.tmall.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource(name = "userDAOHibImpl2")
    private UserDAO userDAO;

    @Override
    public void reset(Integer uid) {
        User user = userDAO.get(uid);
        user.setPassword("123456");
        userDAO.update(user);
    }
}
