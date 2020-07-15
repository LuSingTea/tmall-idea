package edu.fzu.tmall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xpath.internal.operations.Mod;
import edu.fzu.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.fzu.tmall.dao.UserDAO;
import edu.fzu.tmall.dao.UserDAOHibImpl;
import edu.fzu.tmall.pojo.User;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
    @Qualifier(value = "userDAOHibImpl2")
	private UserDAO userDAO;

	@Autowired
    private UserService userService;
	@RequestMapping("/login")
	public String login(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        HttpServletRequest request, Model model) {
        System.out.println(name + password);
        User user = userDAO.get(name, password);
        if (user == null) {
            model.addAttribute("msg", "账号或者密码错误");
            return "login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        List<User> list = userDAO.list();
        for (User user1 : list) {
            System.out.println(user1);
        }
        model.addAttribute("users", list);
        return "admin/listUser";
    }
    /**
     * @Description: 列出用户信息
     */
    @RequestMapping(value = "/listUser.do")
    public String listAppointment(HttpServletRequest request) {
    	List<User> users = userDAO.list();
		request.setAttribute("users", users);
        return "admin/listUser";
    }
    @RequestMapping("/reset")
    public String reset(@RequestParam("uid") Integer uid,
                        Model model) {
        userService.reset(uid);
        return "admin/listUser";
    }

}
