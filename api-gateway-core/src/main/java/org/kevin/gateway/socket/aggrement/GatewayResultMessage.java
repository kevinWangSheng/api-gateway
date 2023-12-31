package org.kevin.gateway.socket.aggrement;

/**
 * @author wang
 * @create 2023-12-31-15:07
 */
public class GatewayResultMessage {
    private String code;

    private String info;

    private Object data;

    public GatewayResultMessage(String code, String info, Object data) {
        this.code = code;
        this.info = info;
        this.data = data;
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

    public static GatewayResultMessage buildSuccess(Object data){
        return new GatewayResultMessage(AggrementConstance.ResponseCode._200.getCode(),AggrementConstance.ResponseCode._200.getCode(),data);
    }

    public static GatewayResultMessage buildFail(String code,String info ){
        return new GatewayResultMessage(code,info,null);
    }
}
