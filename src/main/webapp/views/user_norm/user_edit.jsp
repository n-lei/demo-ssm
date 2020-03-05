<%--
  Created by IntelliJ IDEA.
  User: nl
  Date: 2019/12/15
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/global.jsp" %>
<html>
<head>
<title>用户信息</title>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
window.onload=function(){
    if("op_add" != $("#method").val() ) {
        setSelectEditValue();
    }
};

function setSelectEditValue(){
    //选中单选框的值
    $("input:radio[name='sex'][value='${user.sex}']").attr("checked",true);
    $("input:radio[name='type'][value='${user.type}']").attr("checked",true);
    $("#deptNode option[value='${user.deptNode}']").prop("selected", true);
}
</script>
</head>
<body>
    <form method="post" action="${ctx}/userNorm/doSubmit" >
        <!-- 设置隐藏id-->
        <input type="hidden" id="id"     name="id"     value="${user.id == null ? 0 : user.id}" />
        <input type="hidden" id="model"  name="model"  value="user" />
        <input type="hidden" id="method" name="method" value="${requestScope.method}" />
        <table align="center" border="1" >
            <tr>
                <td colspan="2" align="center">编辑用户信息</td>
            </tr>
            <tr>
                <td>姓名</td>
                <td>
                    <input type="text" id="userName" name="userName" value="${user.userName}"/>
                </td>
            </tr>
            <tr>
                <td>性别</td>
                <td>
                    <input type="radio" name="sex" id="sex_1" value="1"/><label for='sex_1'>男</label>
                    <input type="radio" name="sex" id="sex_2" value="2"/><label for='sex_2'>女</label>
                    <input type="radio" name="sex" id="sex_99" value="99"/><label for='sex_99'>其他</label>
                </td>
            </tr>
            <tr>
                <td>用户类型</td>
                <td>
                    <input type="radio" name="type" id="type_1" value="1"/><label for='type_1'>内网用户</label>
                    <input type="radio" name="type" id="type_2" value="2"/><label for='type_2'>app</label>
                    <input type="radio" name="type" id="type_3" value="3"/><label for='type_3'>外网</label>
                </td>
            </tr>
            <tr>
                <td>部门</td>
                <td>
                    <select id="deptNode" name="deptNode">
                        <option value="0" >系统管理部门</option>
                        <option value="01" >运营中心</option>
                        <option value="02" >养老机构</option>
                        <option value="020001" >养老机构06</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <a href="${ctx}/userNorm/listAllUsers">返回</a>
                </td>
                <td>
                    <input type="submit" value="提交"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>