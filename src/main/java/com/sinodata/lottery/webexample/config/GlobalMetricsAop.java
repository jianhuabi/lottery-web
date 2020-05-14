package com.sinodata.lottery.webexample.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Aspect
public class GlobalMetricsAop {

    @Pointcut("execution(public * com.sinodata.lottery.webexample.controller.*.*(..))")
    public void pointCut(){}

    //统计请求的处理时间
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Autowired
    MeterRegistry registry;

    private Counter counter;
    private AtomicInteger online_count;
    private Timer time_cost;

    @PostConstruct
    private void init(){
        counter = registry.counter("requests_total","status","success");
        online_count = registry.gauge("online_count", new AtomicInteger(0));
        time_cost = registry.timer("time_cost","method","controller");
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        startTime.set(System.currentTimeMillis());
        counter.increment(); //记录系统总请求数
        int people_count = new Random().nextInt(2000);  //模拟一个在线数值
        online_count.set(people_count); //记录在线人数
    }

    @AfterReturning(returning = "returnVal" , pointcut = "pointCut()")
    public void doAfterReturning(Object returnVal){
        //处理完请求后
        long totalTime = System.currentTimeMillis() - startTime.get();
        System.out.println("方法执行时间:"+ totalTime);
        time_cost.record(Duration.ofMillis(totalTime)); //记录请求耗费时间
    }
}
