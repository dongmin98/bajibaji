package com.baji.dom.common;


import com.baji.dom.commonEnum.ENUM_RESTFUL_COMMON_CODE;

public class ResultModel {

	private int code;

	private String message= "";

	private Object content;

	public ResultModel(int code, String message) {
		this.code = code;
		this.message = message;
		this.content = "";
	}
 
	public ResultModel(int code, String message, Object content) {
		this.code = code;
		this.message = message;
		this.content = content;
	}

	public ResultModel() {
	}

	public static com.baji.dom.common.ResultModel ok(Object content) {
		return new com.baji.dom.common.ResultModel(0, "成功", content);
	}

	public static com.baji.dom.common.ResultModel ok() {
		return new com.baji.dom.common.ResultModel(0, "成功");
	}

	//public static ResultModel error(int code, String message) {
	///	return new ResultModel(code, message);
	//}

	public static com.baji.dom.common.ResultModel error(Object[] objects) {
		if(null!=objects && objects.length==2){
			return new com.baji.dom.common.ResultModel(Integer.valueOf(String.valueOf(objects[0])), String.valueOf(objects[1]));
		}else{
			return new com.baji.dom.common.ResultModel(ENUM_RESTFUL_COMMON_CODE.SYSTEM_ERROR.getCode(), ENUM_RESTFUL_COMMON_CODE.SYSTEM_ERROR.getMessage());
		}
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

}
