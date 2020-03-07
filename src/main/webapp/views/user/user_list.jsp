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
<script src="${ctx}/static/js/views/pubList.js"></script>
<script src="${ctx}/static/js/pub/ajaxData.js"></script>
<html>
<head>
    <title>SSM-CRUD</title>
    <%--引入bootstrap的css全局样式--%>
    <link href="${ctx}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <%--引入bootstrap的js插件--%>
    <script src="${ctx}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
<%--
    Bootstrap 提供了栅格系统，栅格系统用于通过一系列的行（row）与列（column）的组合来创建页面布局
    行（row）必须包含在类 .container，你的内容应当放置于“列（column）”内，
    并且只有“列（column）”可以作为行（row）”的直接子元素
    系统会自动分为最多12列，栅格系统中的列是通过指定1到12的值来表示其跨越的范围
--%>
<div class="container">
    <%--第一行 标题--%>
    <div class="row">
        <div class="col-md-12">
            <h1>NL-SSM-DEMO</h1>
        </div>
    </div>
    <%--第二行 增加 删除 按钮--%>
    <div class="row">
        <div class="col-md-4 col-md-offset-10">
            <button class="btn btn-success" id="btn_add">新增</button>
            <button class="btn btn-danger" id="btn_delete_all">删除</button>
        </div>
    </div>
    <%--第三行 表格--%>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="show_dataList_table"></table>
        </div>
    </div>
    <%--第四行 底部导航栏--%>
    <div class="row">
        <%--页面信息--%>
        <div class="col-md-6" id="pageNumber_info_area"></div>
        <%--页面导航--%>
        <div class="col-md-6" id="pageNav_info_area"></div>
    </div>
</div>

<%--引入修改员工信息页面--%>
<jsp:include page="user_edit.jsp"/>

<input type="hidden" id="pagingUrl"      value="${ctx}/user/paging">
<input type="hidden" id="pageSize"       value="10">
<input type="hidden" id="pageShowNumber" value="10">


<script>
    // 页面加载完成后，发起ajax请求，获取json数据
    $(function () {
        ajax_to_page();
    });

    /**
     * 将获取的json数据解析并显示到table员工信息部分
     * @param result
     */
    function build_dataList_info(result) {
        console.log(result);
        // 清空上一页的显示数据
        $("table tbody").empty();
        // 清除th行的全选框选中状态
        $("#checkbox_all").prop("checked", false);

        // 抬头列名数组
        let titleArray = ['ID','用户类型','用户名','性别','部门名称','部门编码','部门父编码','部门等级'];

        let htmlArray = [];
        htmlArray.push("<thead>");
        htmlArray.push("<tr>");
        htmlArray.push("<th><input type=\"checkbox\" id=\"checkbox_all\"/></th>");
        htmlArray.push("<th>#</th>");
        for (let t=0; t<titleArray.length; t++){
            htmlArray.push("<th>"+ titleArray[t] +"</th>");
        }
        htmlArray.push("<th>操作</th>");
        htmlArray.push("</tr>");
        htmlArray.push("</thead>");

        // 取出InfoDTO对象中的员工列表
        let datas = result.data.pageInfo.list;
        // 遍历集合users, 对于每一条记录，执行回调函数
        // 每一条记录，是一个员工信息，将其信息封装到一个tr中，添加到表格中
        $.each(datas, function (index, data) {
            // console.log(data);
            // 每一个属性字段放在一个td里面
            htmlArray.push("<tbody>");
            htmlArray.push("<tr>");
            htmlArray.push("<td><input type='checkbox' class='checkbox_single'></input></td>");
            // 增加html信息 - 内容
            htmlArray.push("<td>" + parseInt(index+1) + "</td>");

            // 列数据值数组
            let columnValueArray = [data.id, data.type, data.userName, getDataDictionaryName("3016", data.sex)
                ,data.dept.name, data.dept.node, data.dept.parentNode, data.dept.level];
            for (let t=0; t<columnValueArray.length; t++){
                htmlArray.push("<td>"+ columnValueArray[t] +"</td>");
            }
            // 增加html信息 - 按钮
            htmlArray.push("<td>");
            htmlArray.push(" <button class='btn btn-info btn-sm btn_edit' index='" + index + "'><span class='glyphicon glyphicon-pencil'>编辑</span></button>");
            htmlArray.push(" <button class='btn btn-danger btn-sm btn_del' index='" + index + "'><span class='glyphicon glyphicon-trash'>删除</span></button>");
            //本条纪录的主键
            htmlArray.push("<input type='hidden' id='pk_id_" + index + "' value='" + data.id + "' />");
            htmlArray.push("</td>");
            htmlArray.push("</tr>");
            htmlArray.push("</tbody>");
        });
        // 将此tr加到table tbody里面
        $("#show_dataList_table").html(htmlArray.join(""));
    }

    /**
     * 为员工信息新增按钮绑定点击事件
     */
    $("#btn_add").click(function () {
        // 打开模态框前，清空上一次的填写信息
        // jquery没有reset()方法，此处$('#empAddModal form')确定不到form表单
        // form_reset("emp_add_form");
        // document.getElementById("emp_add_form").reset();
        // 查出部门列表信息，显示在下拉列表中
        // getDepts("#empAddModal select");
        // 打开用于新增的模态框，并设置属性，点击其他地方时此模态框不会关闭
        $("#editView_dialog").modal({
            backdrop:"static"
        });
    });
</script>
</body>
</html>