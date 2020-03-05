<%--
  Created by IntelliJ IDEA.
  User: nl
  Date: 2019/12/15
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/global_modal.jsp" %>
<script src="${ctx}/static/js/pub/viewForm.js"></script>
<div class="modal fade" id="editView_dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">用户信息</h4>
            </div>
            <div class="modal-body">
                <form id="editView_form" class="form-horizontal" method="post" action="${ctx}/userNorm/doSubmit" >
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="userName" id="userName" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="email" id="email" placeholder="niel@qq.com">
                            <span id="helpBlock2" class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="sex" id="sex_1" value="1"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="sex" id="sex_2" value="2"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户类型</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="type" id="type_1" value="1"> 内网用户
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="type" id="type_2" value="2"> app
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="type" id="type_3" value="3"> 外网
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="deptNode">
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_edit_submit">修改</button>
            </div>
        </div>
    </div>
</div>
<script>
    /**
     * 给所有编辑按钮(class：.btn_edit)绑定点击事件
     * $(".btn_edit").click(function () { alert("edit"); });
     此种方式：页面加载完成会去给具有.btn_edit类的按钮绑定事件，
     但我们的表格数据(包括操作部分的修改和删除按钮)是页面加载完后通过发送ajax请求
     获取数据，再构造出来的,因此此种方式无法给之后创建的按钮绑定上事件
     解决：
     1.在创建按钮的时候绑定事件
     2.使用.live()方法绑定事件，即便是后面才创建出来，也有效
     3.jquery高版本取消了.live()方法，提供了.on()方法，使用
     $("父元素选择器").on("事件名"，"子元素选择器", function () { alert("edit"); });
     */
    /** (遍历document的全部子孙节点，给所有具有.btn_edit的按钮绑定单击事件) */
    $(document).on("click",".btn_edit",function () {
        // 1.查出可选的部门信息显示在下拉列表
        // getDepts("#empUpdateModal select");
        // 2.查出当前员工信息并显示
        let index = $(this).attr("index");
        let pk_id = $("#pk_id_" + index).val();
        setFormDataById(pk_id);
        // 把当前员工id传递给提交按钮
        // $("#emp_edit_submit").attr("emp_id",empId);
        // 3.弹出模态框进行修改操作
        $("#editView_dialog").modal({
            backdrop:"static"
        });
    });

    /**
     * 查询指定id的用户信息并显示
     * @param id
     */
    function setFormDataById(id) {
        $.ajax({
            url:"${ctx}/user/data/"+id,
            type:"GET",
            success:function (result) {
                if(result.success){
                    $("form[id='editView_form']").setFormData(result.data.field);
                }
            }
        });
    }

    /**
     * 遍历document的全部子孙节点，给所有具有.btn_del的按钮绑定单击事件
     */
    $(document).on("click",".btn_del",function () {
        // 当前要删除的员工姓名
        var empName = $(this).parents("tr").find("td:eq(2)").text();
        var empId = $(this).attr("emp_id");
        if(confirm("你是否确定删除员工【"+empName+"】?")){
            // 发送删除员工的请求
            $.ajax({
                url:"${ctx}/user/page/"+empId,
                type:"DELETE",
                success:function (result) {
                    if (result.code == "200"){
                        alert(result.msg);
                        // 跳转到原位置
                        ajax_to_page(currentPage);
                    }
                }
            });
        }
    });

    /**
     * 为修改提交按钮绑定单击事件
     */
    $("#emp_edit_submit").click(function () {
        // 如果邮箱格式正确，就发送更新请求(控制器要求访问方式为PUT)
        if(checkEmail("#emp_edit_email")){
            $.ajax({
                url:"${ctx}/user/update/"+$(this).attr("emp_id"),
                /*
                    方式一：发送POST请求，借助 HiddenHttpMethodFilter 过滤器
                        并给请求体添加参数  "&_method=PUT"  将POST请求转为PUT请求
                    type:"POST",
                    data:$("#empUpdateModal form").serialize()+"&_method=PUT",
                    方式二：直接发送ajax PUT请求, 不用改变参数，要求配置了 HttpPutFormContentFilter 过滤器
                */
                type:"PUT",
                data:$("#empUpdateModal form").serialize(),
                success:function (result) {
                    // 修改成功
                    if(result.code == "200"){
                        // alert(result.msg);
                        // 关闭模态框
                        $("#empUpdateModal").modal("hide");
                        // 跳转到修改了的记录所在的页
                        ajax_to_page(currentPage);
                    }
                }
            });
        }
    });

    /**
     * 为table th行的全选按钮添加点击事件
     */
    $("#checkbox_all").click(function () {
        // attr()获取标签的原生属性是undefined
        // 因此对于自创建的标签，使用prop获取和操作原生属性,使用attr获取自定义属性
        // alert($(this).prop("checked"));
        // 让其他的单选框的选中状态随全选框改变
        $(".checkbox_single").prop("checked", $(this).prop("checked"));
    });
    /**
     * 为每一行的单选框绑定点击事件，当每一页的每一行都选中时，上面的全选框也要随着选中
     * 遍历document的全部子孙节点，给所有具有.checkbox_single 类的按钮绑定单击事件
     */
    $(document).on("click",".checkbox_single",function () {
        // 判断当前页选中的单选框是否等于当前页全部的单选框，若是，则全选框随之选中
        var flag = $(".checkbox_single:checked").length == $(".checkbox_single").length;
        $("#checkbox_all").prop("checked", flag);
    });

    /**
     * 给批量删除按钮(顶部的删除按钮)添加点击事件
     */
    $("#emp_delete_all").click(function () {
        // 获取选中的员工姓名
        var names = "";
        // 获取选中的员工id
        var ids = "";
        // 遍历选中的记录
        $.each($(".checkbox_single:checked"),function (index,item) {
            names += $(this).parents("tr").find("td:eq(2)").text()+",";
            ids += $(this).parents("tr").find("td:eq(1)").text()+"-";
        });
        // 去除names最后一个逗号
        names = names.substring(0,names.length-1);
        // 去除ids最后一个-
        ids = ids.substring(0,ids.length-1);
        if(names.trim().length==0){
            alert("请选择要删除的员工！")
            return false;
        }
        if(confirm("你确定要删除员工【"+names+"】吗？")){
            // 发送ajax请求批量删除
            $.ajax({
                url:"${ctx}/user/dels/"+ids,
                type:"DELETE",
                success:function (result) {
                    if(result.code == "200"){
                        alert(result.msg);
                        ajax_to_page(currentPage);
                    }
                }
            });
        }
    });


    /**
     * 为用户名输入框验证输入内容，并显示校验信息)
     */
    function checkName (nameElementSelector) {
        var name = $(nameElementSelector).val();
        var nameRex = /(^[a-zA-Z0-9_]{3,10}$)|(^[\u2E80-\u9FFF]{2,5}$)/;
        if(!nameRex.test(name)){
            // alert("用户名必须是3-10位字母下划线和数字的组合或者2-5位中文！")
            show_validate_msg(nameElementSelector,"error","用户名必须是3-10位字母下划线和数字的组合或者2-5位中文");
            return false;
        }else {
            show_validate_msg(nameElementSelector,"success","");
            return true;
        }
    }

    /**
     * 为指定的邮箱输入框添加失去焦点时的事件函数(验证输入内容，并显示校验信息)
     */
    function checkEmail (emailElemSelector) {
        var email = $(emailElemSelector).val();
        var emailRex = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
        if(!emailRex.test(email)){
            // alert("邮箱格式不正确！")
            show_validate_msg(emailElemSelector,"error","邮箱格式不正确");
            return false;
        }else {
            show_validate_msg(emailElemSelector,"success","");
            return true;
        }
        /**
         * 未邮箱输入框添加失去焦点事件
         */
        $("#emp_add_email").blur(function () {
            // 验证输入的邮箱格式是否正确
            checkEmail("#emp_add_email");
        });
    }

    /**
     * 校验用户输入的表单数据是否合理
     */
    function validate_form_data() {
        // 校验用户名
        // 校验邮箱
        return checkName("#emp_add_name")&&checkEmail("#emp_add_email");
    }

    /**
     *  发送ajax请求，校验此用户名是否已存在
     */
    function nameExit() {
        var name = $("#emp_add_name").val().trim();
        // 防止未输入时，就去发送请求
        if(name != " " && name.length>0){
            $.ajax({
                url:"${ctx}/emps/check",
                type:"POST",
                data:"name="+name,
                success:function (result) {
                    if(result.code == "200"){
                        // 当前用户名不存在，给input标签添加一个属性值
                        show_validate_msg("#emp_add_name","success","用户名可用");
                        $("#emp_add_name").attr("isExit","false");
                    }else {
                        show_validate_msg("#emp_add_name","error","用户名已存在");
                        $("#emp_add_name").attr("isExit","true");
                    }
                }
            })
        }
    }

    /**
     * 检查用户名是否可用
     */
    function checkNameAvailable() {
        // 手动触发input框失去焦点函数，以保证下面获取的值不是上次的值，而是重新发送ajax请求后设置的值
        nameExit();
        var isExit = $("#emp_add_name").attr("isExit");
        if(isExit == "false"){
            return true;
        }else
            return false;
    }

    /**
     * 为员工信息新增按钮绑定点击事件
     */
    $("#emp_add_btn").click(function () {
        // 打开模态框前，清空上一次的填写信息
        // jquery没有reset()方法，此处$('#empAddModal form')确定不到form表单
        // form_reset("emp_add_form");
        // document.getElementById("emp_add_form").reset();
        // 查出部门列表信息，显示在下拉列表中
        // getDepts("#empAddModal select");
        // 打开用于新增的模态框，并设置属性，点击其他地方时此模态框不会关闭
        $("#empAddModal").modal({
            backdrop:"static"
        });
    });

    /**
     * 为模态框中员工信息保存按钮绑定点击事件
     */
    $("#emp_save_btn").click(function () {
        // alert($("#empAddModal form").serialize());
        // 1. 前端校验数据合理性
        if(!validate_form_data()){
            // 校验失败，直接返回，不发送请求
            return false;
        }
        // 2.检查用户名是否重复
        if(!checkNameAvailable()) {
            return false;
        }
        // 3.发送保存请求
        $.ajax({
            url:"${ctx}/emps/save",
            type:"POST",
            // jquery提供的将表单数据序列化，作为ajax传输时的参数
            data:$("#empAddModal form").serialize(),
            success:function (result) {
                // alert(result.msg);
                // 保存成功
                if(result.code == "200"){
                    // 保存成功后，关闭模态框，清除上一次的数据，跳转到最后一页
                    $('#empAddModal').on('hidden.bs.modal', function () {
                        form_reset("#emp_add_form");
                    });
                    $('#empAddModal').modal('hide');
                    ajax_to_page(totalPage);
                }else{
                    // 取出错误信息
                    var errorMap = result.dataMap.errorMap;
                    // 判断出错的字段，并显示在相应标签
                    // email字段不为undefined说明后端校验邮箱出错
                    if(errorMap.email != undefined){
                        show_validate_msg("#emp_add_email","error",errorMap.email);
                    }
                    if(errorMap.name != undefined){
                        show_validate_msg("#emp_add_name","error",errorMap.name);
                    }
                }
            }
        });
    });
</script>