package edu.fzu.tmall.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 权限验证过滤器
//@WebFilter(dispatcherTypes = {DispatcherType.REQUEST },urlPatterns = { "/*" })
public class AuthorityFilter implements Filter {
    private String logPage = "/login.jsp";

    @Override
    public void destroy() {
        // TODO Auto-generated method st
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String request_uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String uri = request_uri.substring(contextPath.length());
        if (req.getSession().getAttribute("user") == null) {
            if (uri.equals(logPage) || request_uri.indexOf("css/") > 0
                    || request_uri.indexOf("img/") > 0
                    || request_uri.indexOf("scripts/") > 0
                    || uri.equals("/loginServlet")) {
                chain.doFilter(request, response);
                return;
            } else {
                res.sendRedirect("login.jsp");
            }
        } else chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }

}