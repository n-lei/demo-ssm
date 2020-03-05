/**
 * 发送ajax请求，获取指定页数据
 * @param pageNum
 */
function ajax_to_page(pageNum) {
    // 请求分页数据url
    let url = $("#pagingUrl").val();
    // 当前第几页的数据
    pageNum = pageNum || "1";
    // 每页显示数据条数
    let pageSize = $("#pageSize").val() || "20";
    // 显示翻页显示条数
    let pageShowNumber = $("#pageShowNumber").val() || "20";

    $.ajax({
        url:url,
        type:"GET",
        data:"pageNumber=" + pageNum +"&pageSize=" + pageSize +"&pageShowNumber=" + pageShowNumber,
        dataType:"json",
        // result是服务器返回结果(InfoDTO对象)
        success:function (result) {
            console.log(result);
            // 1.解析并显示列表数据信息
            build_dataList_info(result);
            // 2.解析并显示分页数量信息
            build_pageNumber_info(result);
            // 2.解析并显示分页导航信息
            build_pageNav_info(result);
        }
    });
}

/**
 * 将获取的json数据解析并显示到table分页信息部分
 * @param result
 */
function build_pageNumber_info(result) {
    let pageInfo = result.data.pageInfo;
    let html = '当前第 <span class="label label-default">' + pageInfo.pageNum + '</span> 页，\
                    共 <span class="label label-default">' + pageInfo.pages + '</span> 页，\
                    共 <span class="label label-default">' + pageInfo.total + '</span> 条记录';
    $("#pageNumber_info_area").html(html);
}

/**
 * 将获取的json数据解析并显示到table导航信息部分
 * @param result
 */
function build_pageNav_info(result) {
    var pageInfo = result.data.pageInfo;
    // 每个导航数字 1 2 3都在li标签里面，所有li在一个ul里面，ul在nav里面
    let htmlArray = [];
    htmlArray.push("<nav aria-label='Page navigation'>");
    htmlArray.push("<ul class='pagination'>");
    // 绑定事件（不在第一页时，点击首页和上一页才发送请求）
    if(pageInfo.hasPreviousPage == true) {
        // 首页li
        htmlArray.push("<li onclick='ajax_to_page(1)'>");
        htmlArray.push("   <a href=\"#\" aria-label=\"Previous\">首页</a>");
        htmlArray.push("</li>");
        // 上一页li
        htmlArray.push("<li onclick='ajax_to_page( "+ parseInt(pageInfo.pageNum - 1) +")'>");
        htmlArray.push("   <a href=\"#\" aria-label=\"Previous\">&laquo;</a>");
        htmlArray.push("</li>");
    }

    // 遍历此次pageInfo中的导航页，并构建li标签，添加到ul
    $.each(pageInfo.navigatepageNums,function (index, item) {
        // 遍历到当前显示的页，就高亮，且不能点击
        if(pageInfo.pageNum == item){
            htmlArray.push("<li class='active'>");
        }else { // 绑定单击事件 传入页号，获取数据
            htmlArray.push("<li onclick='ajax_to_page(" + item + ")'>");
        }
        htmlArray.push("<a href='javascript:void(0)'>");
        htmlArray.push(item);
        htmlArray.push("</a>");
        htmlArray.push("</li>");
    });

    // 绑定事件（不在末页时，点击尾页和下一页才发送请求）
    if(pageInfo.hasNextPage == true) {
        // 下一页li
        htmlArray.push("<li onclick='ajax_to_page(" + pageInfo.pageNum + 1 + ")'>");
        htmlArray.push("   <a href='javascript:void(0)' aria-label=\"Previous\">&raquo;</a>");
        htmlArray.push("</li>");
        // 尾页li
        htmlArray.push("<li onclick='ajax_to_page(" + pageInfo.pages + ")'>");
        htmlArray.push("   <a href='javascript:void(0)' aria-label=\"Previous\">尾页</a>");
        htmlArray.push("</li>");
    }

    htmlArray.push("</ul>");
    htmlArray.push("</nav>");
    $("#pageNav_info_area").html(htmlArray.join(""));
}