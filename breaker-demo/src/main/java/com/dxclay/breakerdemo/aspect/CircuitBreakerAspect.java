package com.dxclay.breakerdemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

//@Aspect
//@Component
public class CircuitBreakerAspect {

    //阈值
    private static final Integer THRESHOLD = 3;
    //发送错误的次数
    private Map<String, AtomicInteger> counter = new ConcurrentHashMap<>();
    //熔断的次数
    private Map<String, AtomicInteger> breakerCounter = new ConcurrentHashMap<>();

//    @Around("execution(* com.dxclay.breakerdemo.service..*(..))")
    public Object doWithCircuitBreaker(ProceedingJoinPoint point) throws Throwable {

        String signature = point.getSignature().toLongString();
        System.out.println("Invoke " + signature);

        Object proceedVal;
        try {
            if (counter.containsKey(signature)){
                if (counter.get(signature).get() > THRESHOLD && breakerCounter.get(signature).get() < THRESHOLD){
                    //增加熔断的次数，当熔断的次数大于阈值时，重试请求
                    System.out.println("Circuit breaker return ------, break "+breakerCounter.get(signature).incrementAndGet()+" times.");
                    return "------";
                }
            }else {
                counter.put(signature, new AtomicInteger(0));
                breakerCounter.put(signature, new AtomicInteger(0));
            }
            proceedVal = point.proceed();
            //请求正常，熔断次数清空
            counter.get(signature).set(0);
            breakerCounter.get(signature).set(0);
        } catch (Throwable throwable) {
            //增加请求错误的次数，当请求错误的次数大于阈值时，进入熔断
            System.out.println("Circuit breaker counter: "+counter.get(signature).incrementAndGet()+" times.error : " + throwable.getMessage());
            breakerCounter.get(signature).set(0);
            throw throwable;
        }
        return proceedVal;
    }

}
