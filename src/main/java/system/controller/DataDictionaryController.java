package system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springMvc.pojo.RespBody;
import system.utils.DataDictionaryUtil;

@Controller
@RequestMapping("/dataDictionary")
public class DataDictionaryController {

    /**
     * 获得数据字典的值
     */
    @ResponseBody
    @GetMapping("/name")
    public RespBody getDataDictionaryName(
            @RequestParam(value = "parent") String parent,
            @RequestParam(value = "value", required = false, defaultValue = "") String value){
        String name = DataDictionaryUtil.getName(parent, value);
        return RespBody.success().addData("name", name);
    }
}