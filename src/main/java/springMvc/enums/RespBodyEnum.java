package springMvc.enums;

/**
 * <p>title: 反馈信息枚举信息
 * @author: niel  @version: v1.0
 */
public enum RespBodyEnum {
	SUCCESS("200", "成功"),
	FAIL("100", "失败")
	;

	private String code;
	private String msg;

	RespBodyEnum(String code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
