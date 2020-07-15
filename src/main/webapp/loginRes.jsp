<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		String name= request.getParameter("name");
		String password=request.getParameter("password");
		if(name.equals("admin") && password.equals("123456")){
		request.getRequestDispatcher("userServlet").forward(request, response); 
	%>
	<%} else
		out.println("登录失败");
	 %>
</body>
</html>