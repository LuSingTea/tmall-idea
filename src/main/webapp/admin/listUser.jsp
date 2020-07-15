<%@page import="java.sql.DriverManager"%>
<%@page import="com.mysql.jdbc.Driver"%>
<%@page import="edu.fzu.tmall.dao.UserDAOImpl,java.util.*"%>
<%@page import="edu.fzu.tmall.dao.UserDAO,edu.fzu.tmall.pojo.User"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
    $(function () {
        $("#a_reset").click(function (e) {
            $.ajax({
                url: "user/reset",
                method: "post",
                data: {

                },
                success: function () {

                },
                error: function () {

                }
            })
            e.preventDefault();
        })
    })
</script>
<div class="workingArea">
	<h1 class="label label-info">用户管理</h1>
	<br> <br>
	<div class="listDataTableDiv">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr class="success">
					<th>ID</th>
					<th>用户名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>			
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td><a href="#" id="a_reset">重置密码</a></td>
                    </tr>

                </c:forEach>

			</tbody>
		</table>
	</div>
	<div class="pageDiv">
		<%--  <%@include file="../include/admin/adminPage.jsp" %> --%>
	</div>
</div>

<%@include file="../include/admin/adminFooter.jsp"%>

