package org.threepixeldev.saungeraadmin.shared.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class CacheLoggingAspect {

    @Pointcut("@annotation(org.springframework.cache.annotation.Cacheable)")
    public void cacheableMethods() {
    }

    @Pointcut("@annotation(org.springframework.cache.annotation.CacheEvict)")
    public void cacheEvictMethods() {
    }

    @Pointcut("@annotation(org.springframework.cache.annotation.CachePut)")
    public void cachePutMethods() {
    }

    @Around("cacheableMethods() && @annotation(cacheable)")
    public Object logCacheable(ProceedingJoinPoint joinPoint, Cacheable cacheable) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        String cacheName = Arrays.toString(cacheable.value());
        Object[] args = joinPoint.getArgs();

        log.info("Cache MISS: Method {} - Cache: {} - Args: {}", methodName, cacheName, Arrays.toString(args));
        
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - startTime;

        if (result != null) {
            if (result instanceof java.util.Collection) {
                int size = ((java.util.Collection<?>) result).size();
                log.info("Cache STORE: Method {} - Cached {} items in {}ms - Cache: {}", 
                    methodName, size, duration, cacheName);
            } else {
                log.info("Cache STORE: Method {} - Cached result in {}ms - Cache: {}", 
                    methodName, duration, cacheName);
            }
        } else {
            log.info("Cache STORE: Method {} - Cached null result in {}ms - Cache: {}", 
                methodName, duration, cacheName);
        }

        return result;
    }

    @Around("cacheEvictMethods() && @annotation(cacheEvict)")
    public Object logCacheEvict(ProceedingJoinPoint joinPoint, CacheEvict cacheEvict) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        String cacheName = Arrays.toString(cacheEvict.value());
        boolean allEntries = cacheEvict.allEntries();
        String key = cacheEvict.key();

        String evictType = allEntries ? "ALL entries" : "key: " + key;
        log.info("Cache EVICT: Method {} - {} from cache: {} - Args: {}", 
            methodName, evictType, cacheName, Arrays.toString(joinPoint.getArgs()));

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - startTime;

        log.info("Cache EVICT COMPLETE: Method {} - Completed in {}ms", methodName, duration);
        return result;
    }

    @Around("cachePutMethods() && @annotation(cachePut)")
    public Object logCachePut(ProceedingJoinPoint joinPoint, CachePut cachePut) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        String cacheName = Arrays.toString(cachePut.value());
        String key = cachePut.key();

        log.info("Cache PUT: Method {} - Updating cache: {} with key: {} - Args: {}", 
            methodName, cacheName, key, Arrays.toString(joinPoint.getArgs()));

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - startTime;

        log.info("Cache PUT COMPLETE: Method {} - Updated cache in {}ms", methodName, duration);
        return result;
    }
}
