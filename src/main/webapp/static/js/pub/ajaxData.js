/**
 * 发送ajax请求，获取指定页数据
 * @param pageNum
 */
function getDataDictionaryName(parent, value) {
    let name;
    $.ajax({
        async:false,
        url:'dataDictionary/name',
        type:"GET",
        data:"parent=" + parent +"&value=" + value,
        // result是服务器返回结果(InfoDTO对象)
        success:function (result) {
            // console.log(result);
            name = result.data.name;
            // return result.data.name;
        }
    });
    // console.log(name);
    return name;
}