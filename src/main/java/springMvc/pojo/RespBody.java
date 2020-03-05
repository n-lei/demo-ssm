package springMvc.pojo;
import springMvc.enums.RespBodyEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: niel
 * @Description: 反馈json对象
 **/
public class RespBody {

    private String code;
    private String msg;
    private Boolean success;
    private Map<String, Object> data = new HashMap<>();
    
    // 操作成功时，返回一个存储了成功信息的传输对象
    public static RespBody success(){
        RespBody respBody = new RespBody();
        respBody.setCode(RespBodyEnum.SUCCESS.getCode());
        respBody.setMsg(RespBodyEnum.SUCCESS.getMsg());
        respBody.setSuccess(true);
        return respBody;
    }

    // 操作失败时，返回一个存储了失败信息的传输对象
    public static RespBody fail(){
        RespBody respBody = new RespBody();
        respBody.setCode(RespBodyEnum.FAIL.getCode());
        respBody.setMsg(RespBodyEnum.FAIL.getMsg());
        respBody.setSuccess(false);
        return respBody;
    }
    
    // 用于添加数据的方法，并使其可以链式操作
    public RespBody addData(String key, Object value){
        this.getData().put(key, value);
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
