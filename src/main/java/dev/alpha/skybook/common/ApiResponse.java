package dev.alpha.skybook.common;
import java.time.LocalDateTime;
public record ApiResponse<T>(boolean success,String message,T data,LocalDateTime timestamp) {
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true,message,data,LocalDateTime.now());
    }
    public static <T> ApiResponse<T> failure(String message) {
        return new ApiResponse<>(false,message,null,LocalDateTime.now());
    }
}