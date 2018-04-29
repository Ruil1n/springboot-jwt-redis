package cn.rui0.exp;

import cn.rui0.util.ResponseData;
import cn.rui0.util.ReturnJson;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return ReturnJson.jsonData(ResponseData.serverInternalError(),e.getMessage(), 0);
//        return "err:" + e.getMessage();
    }
}
