package org.kevin.gateway.executor.result;

/**
 * @author wang
 * @create 2023-12-30-21:56
 */
public class SessionResult {
    private String code;
    private String info;

    private Object data;

    public SessionResult(String code, String info, Object data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public static SessionResult buildSuccess(Object data){
        return new SessionResult("0000","success",data);
    }

    public static SessionResult buildFail(String message ){
        return new SessionResult("0001",message,null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
