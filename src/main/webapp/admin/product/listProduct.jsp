<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../include/admin/adminHeader.jsp"%>
<%@include file="../../include/admin/adminNavigator.jsp"%>

<script>
    $(function() {
        $("#addForm").submit(function() {
            if (!checkEmpty("name", "产品名称"))
                return false;
            if (!checkEmpty("subTitle", "小标题"))
                return false;
            if (!checkNumber("originalPrice", "原价格"))
                return false;
            if (!checkNumber("promotePrice", "优惠价格"))
                return false;
            if (!checkInt("stock", "库存"))
                return false;
            return true;
        });
    });
</script>

<br><br>
<br><br>

<title>产品管理</title>
<div class="workingArea">
    <ol class="breadcrumb">
        <li class="active">商品管理/li>
    </ol>

    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>产品类别</th>
                <th>产品名称</th>
                <th>产品小标题</th>
                <th width="53px">原价格</th>
                <th width="80px">优惠价格</th>
                <th width="80px">库存数量</th>
                <th width="80px">图片管理</th>
                <th width="80px">设置属性</th>
                <th width="42px">编辑</th>
                <th width="42px">删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.category.name}</td>
                    <td>${product.name}</td>
                    <td>${product.subTitle}</td>
                    <td>${product.originalPrice}</td>
                    <td>${product.promotePrice}</td>
                    <td>${product.stock}</td>
                    <td><a href="admin_productImage_list?product.id=${product.id}"><span
                            class="glyphicon glyphicon-picture"></span></a></td>
                    <td><a href="admin_propertyValue_edit?product.id=${product.id}"><span
                            class="glyphicon glyphicon-th-list"></span></a></td>
                    <td><a  href="product/editProduct.do?id=${product.id}"><span
                            class="glyphicon glyphicon-edit"></span></a></td>
                    <td><a deleteLink="true"
                           href="product/deleteProduct.action?id=${product.id}"><span
                            class="glyphicon glyphicon-trash"></span></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
	<% String reqUri="product/listProductByPage.do"; %>
    <div class="pageDiv">
        <%@include file="../../include/admin/adminPage.jsp"%>
    </div>
<%--    <script>--%>
<%--        $(function () {--%>
<%--            $("ul.pagination li.disabled a").click(function () {--%>
<%--                return false;--%>
<%--            });--%>
<%--        });--%>

<%--    </script>--%>
<%--    <nav>--%>
<%--        <ul class="pagination">--%>
<%--            <li <c:if test="${!page.hasPreviouse}">class="disabled"</c:if>>--%>
<%--                <a href="<%=reqUri%>?start=0${page.param}" aria-label="Previous">--%>
<%--                    <span aria-hidden="true">&laquo;</span>--%>
<%--                </a>--%>
<%--            </li>--%>

<%--            <li <c:if test="${!page.hasPreviouse}">class="disabled"</c:if>>--%>
<%--                <a href="<%= reqUri %>?start=${page.start-page.count}${page.param}" aria-label="Previous">--%>
<%--                    <span aria-hidden="true">&lsaquo;</span>--%>
<%--                </a>--%>
<%--            </li>--%>

<%--            <c:forEach begin="0" end="${page.totalPage-1}" varStatus="status">--%>
<%--                <c:if test="${status.count*page.count-page.start<=20 && status.count*page.count-page.start>=-10}">--%>
<%--                    <li <c:if test="${status.index*page.count==page.start}">class="disabled"</c:if> >--%>
<%--                        <a href="<%= reqUri %>?start=${status.index*page.count}${page.param}"--%>
<%--                           <c:if test="${status.index*page.count==page.start}">class="current"</c:if>>--%>
<%--                                ${status.count}--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                </c:if>--%>
<%--            </c:forEach>--%>

<%--            <li <c:if test="${!page.hasNext}">class="disabled"</c:if>>--%>
<%--                <a href="<%= reqUri %>?start=${page.start+page.count}${page.param}" aria-label="Next">--%>
<%--                    <span aria-hidden="true">&rsaquo;</span>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <li <c:if test="${!page.hasNext}">class="disabled"</c:if>>--%>
<%--                <a href="<%=reqUri%>?start=${page.last}${page.param}" aria-label="Next">--%>
<%--                    <span aria-hidden="true">&raquo;</span>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--        </ul>--%>
<%--    </nav>--%>
<%--</div>--%>

<%@include file="../../include/admin/adminFooter.jsp"%>