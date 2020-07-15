<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.fzu.tmall.dao.*,java.util.*,edu.fzu.tmall.pojo.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%				
	UserDAO userDAO = new UserDAOImpl();
	List<User> users = userDAO.list();
	request.setAttribute("users", users);
	request.getRequestDispatcher("listUser.jsp").forward(request, response);
%>
</body>
</html>