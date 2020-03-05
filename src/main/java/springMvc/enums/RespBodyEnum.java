package springMvc.enums;

/**
 * <p>title: ������Ϣö����Ϣ
 * @author: niel  @version: v1.0
 */
public enum RespBodyEnum {
	SUCCESS("200", "�ɹ�"),
	FAIL("100", "ʧ��")
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
