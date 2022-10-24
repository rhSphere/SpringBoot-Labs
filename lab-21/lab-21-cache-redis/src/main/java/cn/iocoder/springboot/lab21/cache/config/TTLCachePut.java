package cn.iocoder.springboot.lab21.cache.config;

import org.springframework.cache.annotation.CachePut;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@CachePut(cacheResolver = "ttlCacheResolver")
public @interface TTLCachePut {

    @AliasFor(annotation = CachePut.class)
    String[] value() default {};

    @AliasFor(annotation = CachePut.class)
    String[] cacheNames() default {};

    @AliasFor(annotation = CachePut.class)
    String key() default "";

    @AliasFor(annotation = CachePut.class)
    String keyGenerator() default "";

    @AliasFor(annotation = CachePut.class)
    String cacheManager() default "";

    @AliasFor(annotation = CachePut.class)
    String condition() default "";

    @AliasFor(annotation = CachePut.class)
    String unless() default "";

    /**
     * time to live
     */
    long ttl() default 0L;

    /**
     * 时间单位
     *
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}

