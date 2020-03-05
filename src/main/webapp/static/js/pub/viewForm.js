/**
 * 表单赋值
 * @param data
 */
$.fn.setFormData = function (data) {
    for (let key in data) {
        let $this = this;
        let $tag = $this.find('[name=' + key + ']');
        if ($tag.length > 1 ) {
            $tag.prop('checked',false);
            if(data[key]){
                let item = data[key].split(',');
                $.each(item, function (i, val) {
                    let $curTag = $this.find('[name=' + key + '][value=' + val + ']');
                    $curTag.prop('checked', true);
                })
            }
        } else {
            $tag.val(data[key])
        }
    }
};
/**
 * 获取表单值
 * @returns {{}}
 */
$.fn.getFormData = function () {
    let obj = {};
    let $tags = this.find('[name]');
    $tags.each(function () {
        let key = $(this).attr('name');
        if (obj.hasOwnProperty(key) && $(this).is(':checked')) {
            obj[key] = obj[key] + ',' + $(this).val();
            return true ;
        }
        let isCheck= $(this).is(function(){
            let type = $(this).attr('type');
            return type =='checkbox' || type =='radio'
        });
        if(isCheck){
            if($(this).is(':checked')){
                obj[key] = $(this).val();
            }
            return true;
        }else{
            obj[key] = $(this).val();
        }
    });
    return obj;
};