package com.vasyerp.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vasyerp.exception.CustomizedException;
import com.vasyerp.service.RateLimiterService;

@Component
@Aspect
public class RateLimiterAspect {

    @Autowired
    private RateLimiterService rateLimiterService;

    @Pointcut("execution(* com.vasyerp.controller.*.*(..)) && args(apiKey,..)")
    public void rateLimitedEndpoint(String apiKey) {}

    @Before("rateLimitedEndpoint(apiKey)")
    public void checkRateLimit(String apiKey) throws CustomizedException {
        rateLimiterService.checkRateLimit(apiKey);
    }
}
