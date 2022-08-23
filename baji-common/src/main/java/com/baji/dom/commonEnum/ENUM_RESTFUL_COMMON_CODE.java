package com.baji.dom.commonEnum;

public enum ENUM_RESTFUL_COMMON_CODE {
    SYSTEM_ERROR (40001,"服务运行异常"),
    NOT_AUTHORIZATION(40002,"接口没有授权"),
    HTTP_MESSAGE_NOT_READABLE (40003,"请求参数解析异常"),
    NOT_METHOD(40004,"接口不存在");

    private int code;

    private String message;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }


    ENUM_RESTFUL_COMMON_CODE(int code, String message){
        this.code = code;
        this.message = message;
    }

    public static String getMessageByCode(String code) {
        for(ENUM_RESTFUL_COMMON_CODE obj : ENUM_RESTFUL_COMMON_CODE.values()) {
            if(code.equals(obj.getCode())) {
                return obj.getMessage();
            }
        }
        return null;
    }

    public String toJsonString() {
        return "{\"code\":"+this.code+", \"message\":\""+this.message+"\"}";
    }

    public Object[] toErrorModel(String... msg) {
        if(null!=msg && msg.length>0 && null!=msg[0] && (!"".equals(msg[0].trim()))){
            return new Object[]{this.code, (this.message+","+msg[0])};
        }else{
            return new Object[]{this.code, this.message};
        }
    }
}
