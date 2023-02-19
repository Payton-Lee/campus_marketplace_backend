package cn.com.campus.marketplace.entity.result;

import lombok.Data;

@Data
public class ResultData <T> {
    private int status;
    private String message;
    private T data;
    public static <T> ResultData<T> success(int code, String message, T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(code);
        resultData.setMessage(message);
        resultData.setData(data);
        return resultData;
    }
    public static <T> ResultData<T> fail(int code, String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(code);
        resultData.setMessage(message);
        return resultData;
    }
    public static <T> ResultData<T> fail(int code, String message, T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(code);
        resultData.setMessage(message);
        resultData.setData(data);
        return resultData;
    }
}