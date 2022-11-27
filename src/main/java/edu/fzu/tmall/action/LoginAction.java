package edu.fzu.tmall.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
    private String name;
    private String password;

    public String verify() {
        String result = null;
        if (name.equals("admin") && password.equals("123456")) {
            Map<String, Object> session = ActionContext.getContext().getSession();
            session.put("user", name);
            result = "success";
        } else {
            result = LOGIN;
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}