package rca.ac.bank.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    public boolean success;
    public String message;
    public Object data;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static ApiResponse success(String message,Object data){
        return new ApiResponse(true,message,data);
    }

    public static ApiResponse success(String message){
        return new ApiResponse(true,message);
    }

    public static ApiResponse error(String message,Object data){
        return new ApiResponse(false,message,data);
    }
    public static ApiResponse error(String message){
        return new ApiResponse(false,message);
    }

}
