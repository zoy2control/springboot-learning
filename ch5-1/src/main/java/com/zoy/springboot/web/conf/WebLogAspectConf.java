package com.zoy.springboot.web.conf;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by zouzp on 2019/2/14.
 */
@Aspect
@Component
public class WebLogAspectConf {
    private static final Logger logger = LoggerFactory.getLogger(WebLogAspectConf.class);

    /*
    * ·AOP同步问题:
    * 在WebLogAspect切面中，分别通过doBefore和doAfterReturning两个独立函数实现了切点头部和切点返回后执行的内容，若我们想统计请求的处理时间，
    * 就需要在doBefore处记录时间，并在doAfterReturning处通过当前时间与开始处记录的时间计算得到请求处理的消耗时间。
    * 那么我们是否可以在WebLogAspect切面中定义一个成员变量来给doBefore和doAfterReturning一起访问呢？是否会有同步问题呢？
    * */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.zoy.springboot.web..*.*(..))")
    public void webLog() {}

    /**
     * ·居然运行不了，这里 RequestAttributes需要请求信息，而服务起不来根本，不能输入 http://127.0.0.1:8080/index进行请求。矛盾
     * @param joinPoint
     */
//    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("URL : ", request.getRequestURL());
        logger.info("HTTP_METHOD : ", request.getMethod());
        logger.info("IP : ", request.getRemoteAddr());
        // ·注意，这里用到 JoinPoint
        logger.info("CLASS_METHOD : ", joinPoint.getSignature().getDeclaringType(),".", joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        startTime.set(System.currentTimeMillis());// ·起始时间
    }

//    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        logger.info("RETURNING : " + ret);
        logger.info("消耗时间 ： " + (System.currentTimeMillis() - startTime.get()));
    }
}
