package fr.karspa.hikerthinkerv3.utils.responses;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseModel<T>{

    private String code;
    private String message;

    private T data;

    public static <T> ResponseModel<T> buildResponse(String code, String message, T data) {
        ResponseModel<T> response = new ResponseModel<>();

        response.code = code;
        response.message = message;
        response.data = data;
        
        return response;
    }

}
