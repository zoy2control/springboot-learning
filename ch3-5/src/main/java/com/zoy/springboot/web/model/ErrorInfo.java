package com.zoy.springboot.web.model;

import lombok.Data;

/**
 * ·统一 Json异常
 * Created by zouzp on 2019/2/11.
 */
@Data
public class ErrorInfo<T> {

    public static final Integer OK = 1;
    public static final Integer ERROR = 0;

    private Integer code;
    private String message;
    private String url;
    private T data;
}
