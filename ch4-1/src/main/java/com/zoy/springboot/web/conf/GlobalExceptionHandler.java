package com.zoy.springboot.web.conf;

import com.zoy.springboot.web.exception.JsonException;
import com.zoy.springboot.web.model.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zouzp on 2019/2/11.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * ·此处返回 html内容
     * @param request
     * @param e
     * @return
     */
    // ·通过 @ExceptionHandler()来匹配不同异常，设置对应的 handler
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e);
        mv.addObject("url", request.getRequestURL());
        mv.setViewName("default_error");
        return mv;
    }

    /**
     * ·返回 Json格式，实现 RESTful API
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = JsonException.class)
    @ResponseBody // ·@ResponseBody可以 返回json对象
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest request, Exception e) {
        ErrorInfo<String> errorInfo = new ErrorInfo<>();
        errorInfo.setMessage(e.getMessage());
        errorInfo.setCode(ErrorInfo.ERROR);// ·不可以用 errorInfo.ERROR来取值，因为 static值不可以通过 引用引入
        errorInfo.setUrl(request.getRequestURL().toString());
        errorInfo.setData("这是 Json格式的异常");
        return errorInfo;
    }

}
