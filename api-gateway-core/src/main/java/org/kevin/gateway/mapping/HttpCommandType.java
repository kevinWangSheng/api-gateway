package org.kevin.gateway.mapping;

/**
 * @author wang
 * @create 2023-12-29-17:41
 */
public enum HttpCommandType {

    POST("POST"),
    GET("GET"),
    PUT("PUT"),
    DELETE("DELETE");

    private String value;

    HttpCommandType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static HttpCommandType getType(String value){
        for(HttpCommandType type:values()){
            if(type.getValue().equalsIgnoreCase(value)){
                return type;
            }
        }
        return null;
    }
}
