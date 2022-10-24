package cn.iocoder.springboot.lab21.cache.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.SimpleCacheResolver;

import java.util.Collection;
import java.util.Optional;

/**
 * @author ludepeng
 * @date 2022-10-21 22:33
 */
public class TTLCacheResolver extends SimpleCacheResolver {

    public TTLCacheResolver() {
    }

    public TTLCacheResolver(CacheManager cacheManager) {
        super(cacheManager);
    }

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        TTLCacheable ttlCacheable = context.getMethod().getAnnotation(TTLCacheable.class);
        TTLCachePut ttlCachePut = context.getMethod().getAnnotation(TTLCachePut.class);

        CacheManager cacheManager = super.getCacheManager();
        if (cacheManager instanceof TTLRedisCacheManager) {
            TTLRedisCacheManager ttlRedisCacheManager = (TTLRedisCacheManager) cacheManager;
            Optional.ofNullable(ttlCacheable).ifPresent(cacheable -> {
                ttlRedisCacheManager.setTtl(cacheable.ttl());
                ttlRedisCacheManager.setTimeUnit(cacheable.timeUnit());
            });
            Optional.ofNullable(ttlCachePut).ifPresent(cachePut -> {
                ttlRedisCacheManager.setTtl(cachePut.ttl());
                ttlRedisCacheManager.setTimeUnit(cachePut.timeUnit());
            });
        }

        return super.resolveCaches(context);
    }

}


