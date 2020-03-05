<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>

<html>
<head>
    <title>Title</title>
    <%--引入jquery--%>
    <script src="${ctx}/static/js/jquery/jquery-3.4.1.min.js"></script>
    <%--引入bootstrap的css全局样式--%>
    <link href="${ctx}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <%--引入bootstrap的js插件--%>
    <script src="${ctx}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>

<body>
    <h2>Hello ssmMaven!</h2>
    <h1>NL-SSM-CRUD-首页</h1><br><br>
    <%--<h3>--%>
        <%--<a href="${ctx}/userNorm/listAllUsers">点击此处查看员工列表(JSP页面解析形式)</a>--%>
    <%--</h3>--%>
    <%--<br/><br/>--%>
    <%--<h3>--%>
        <%--<a href="${ctx}/user_rest">点击此处查看员工列表(AJAX加JSON解析形式)</a>--%>
    <%--</h3>--%>

    <ul class="nav nav-pills nav-stacked">
        <li role="presentation" >
            <a href="${ctx}/userNorm/listAllUsers">点击查看用户列表(普通url链接请求)</a>
        </li>
        <li role="presentation">
            <a href="${ctx}/user_rest">点击查看用户列表(RestFul风格)</a>
        </li>
        <li role="presentation" class="active">
            <a href="${ctx}/user">点击查看用户列表(完善版)</a>
        </li>
    </ul>
</body>
</html>