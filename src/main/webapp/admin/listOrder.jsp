<%@ page import="org.springframework.ui.Model" %>
<%@ page import="edu.fzu.tmall.pojo.Order" %><%--
  Created by IntelliJ IDEA.
  User: a1725
  Date: 2020/5/2
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<style>
    .active {
        display: block;
    }
</style>
<script>
    $(function() {
        $("#addForm").submit(function() {
            if (checkEmpty("name", "订单名称"))
                return true;
            return false;
        });
        $(".orderPageCheckOrderItems").click(function() {
            console.log("弹出了");
            var oid = $(this).attr("oid");
            $("tr.orderPageOrderItemTR[oid=" + oid + "]").toggle("active");
        });
    });
</script>
<div class="workingArea">
    <h1 class="label label-info">订单管理</h1>
    <br> <br>
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>状态</th>
                <th>金额</th>
                <th>商品数量</th>
                <th>卖家名称</th>
                <th>创建时间</th>
                <th>支付时间</th>
                <th>发货时间</th>
                <th>确认收货时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.statusDesc}</td>
                        <td>${order.address}</td>
                        <th>${order.orderItems.size()}</th>
                        <th>${order.user.name}</th>
                        <th>${order.createDate}</th>
                        <th>${order.payDate}</th>
                        <th>${order.deliveryDate}</th>
                        <th>${order.confirmDate}</th>
                        <td>
                            <button oid=${order.id} class="orderPageCheckOrderItems btn btn-primary">查看详情</button>
                            <c:choose>
                                <c:when test="${order.statusDesc eq '待收'}">
                                    <a href="order/delivery.do?id=${order.id}"><button type="button" class="btn btn-success" style="display: none">发货</button></a>
                                </c:when>
                                <c:otherwise>
                                    <a href="order/delivery.do?id=${order.id}"><button type="button" class="btn btn-primary">发货</button></a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr class="orderPageOrderItemTR" oid=${order.id}>
                        <td colspan="10" align="center">
                            <div class="orderPageOrderItem">
                                <table width="800px" align="center" class="orderPageOrderItemTable">
                                    <c:forEach items="${order.orderItems}" var="oi">
                                        <tr>
                                            <td><a href="foreproduct?product.id=${oi.product.id}">
                                                <span>${oi.product.name}</span>
                                            </a></td>
                                            <td align="right"><span class="text-muted">${oi.number}个</span>
                                            </td>
                                            <td align="right"><span class="text-muted">单价：￥${oi.product.promotePrice}</span>
                                            </td>

                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>

                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <% String reqUri="order/listOrderByPage.do"; %>
    <div class="pageDiv">
          <%@include file="../include/admin/adminPage.jsp" %>
    </div>
</div>
<%@include file="../include/admin/adminFooter.jsp"%>