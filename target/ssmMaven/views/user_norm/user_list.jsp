<%--
  Created by IntelliJ IDEA.
  User: nl
  Date: 2019/12/15
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/global.jsp" %>
<html>
<head>
    <title>用户列表</title>
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <h1 align="center">用户信息列表</h1>
    <span style="float: right;padding-right: 10px;">
        <button onclick="location.href='${ctx}/user/goAdd'" style="font-size:18px;">新增</button>
    </span>
    <table align="center" border="1" width="100%" cellspacing="0px">
        <tr>
            <th>ID</th>
            <th>用户类型</th>
            <th>用户名</th>
            <th>性别</th>
            <th>部门名称</th>
            <th>部门编码</th>
            <th>部门父编码</th>
            <th>部门等级</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${requestScope.user}" var="data">
            <tr align="center">
                <td>${data.id}</td>
                <td>${data.type}</td>
                <td>${data.userName}</td>
                <td>${func:getDataDictionaryName("3016", data.sex)}</td>
                <td>${data.dept.name}</td>
                <td>${data.deptNode}</td>
                <td>${data.dept.parentNode}</td>
                <td>${data.dept.level}</td>
                <td>
                    <button onclick="editById('${data.id}')">修改</button>
                    <button onclick="deleteById(${data.id})">删除</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</head>
<body>
</body>
</html>
<script>
    /**
     * 修改数据
     * @param id
     */
    function editById(id) {
        location.href='${ctx}/user/goEdit/' + id;
        //location.href='${ctx}/user/goEdit?id=' + id;
    }

    /**
     * 删除数据
     * @param id
     */
    function deleteById(id) {
        if(confirm("确定要删除该条数据吗?")){
            location.href='${ctx}/user/delete?id=' + id;
        }
    }
</script>