package com.xd.servicebase.exceptionhandler;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Data
@AllArgsConstructor//生成有参数构造方法
@NoArgsConstructor//生成无参数构造方法
public class xdException extends RuntimeException{

    private Integer code;//状态码

    private String msg;//异常信息
}
