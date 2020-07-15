<%@ page import="edu.fzu.tmall.pojo.Order"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/admin/adminHeader.jsp"%>
<%@ include file="../include/admin/adminNavigator.jsp"%>

<title>查看订单</title>


<div class="workingArea">
    <%--  面包屑 --%>
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有订单</a></li>
        <li><a href="order/listOrderByPage.do?id=${order.id}">${order.orderCode}</a></li>
        <li class="active">${order.orderCode}</li>
        <li class="active">查看订单</li>
    </ol>

    <div class="panel panel-warning editDiv">
        <div class="panel-heading">查看订单</div>
        <div class="panel-body">
            <form method="post" id="editForm"
                  action="product/updateProduct.action">
                <table class="editTable">
                    <tr>
                        <td>订单状态</td>
                        <td><input id="name" name="product.name"
                                   value="${order.status}" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>订单金额</td>
                        <td><input id="subTitle" name="product.subTitle" type="text"
                                   value="${order.orderItems.size()}" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>买家名称</td>
                        <td><input id="originalPrice"
                                   value="${order.user.name}" name="product.originalPrice"
                                   type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>优惠价格</td>
                        <td><input id="promotePrice" value="${order.promotePrice}"
                                   name="product.promotePrice" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>库存</td>
                        <td><input id="stock" value="${product.stock}"
                                   name="product.stock" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>类别</td>
                        <td>
                            <select name="product.category.id" class="form-control">
                                <%
                                    for (Category category : categories) {
                                %>
                                <option value="<%=category.getId()%>"  <%if(category.getId()==product.getCategory().getId()) out.println(" selected");%>>
                                    <%=category.getName()%>
                                </option>
                                <%}%>
                            </select>
                        </td>


                    </tr>

                    <tr class="submitTR">
                        <td colspan="2" align="center"><input type="hidden"
                                                              name="product.id" value="${product.id}">
                            <button type="submit" class="btn btn-success">提 交</button></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
