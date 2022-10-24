package cn.iocoder.springboot.lab21.cache.config;

import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ludepeng
 * @date 2022-10-21 22:33
 */
public class TTLRedisCacheManager extends RedisCacheManager {

    /**
     * 过期时间，具体见{@link com.netease.cache.distrubuted.redis.integration.custom.annotation.TTLCacheable}
     * 中的ttl说明
     */
    private long ttl;

    /**
     * 时间单位
     */
    private TimeUnit timeUnit;

    public TTLRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    public TTLRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration,
                                String... initialCacheNames) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheNames);
    }

    public TTLRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration,
                                boolean allowInFlightCacheCreation, String... initialCacheNames) {
        super(cacheWriter, defaultCacheConfiguration, allowInFlightCacheCreation, initialCacheNames);
    }

    public TTLRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration,
                                Map<String, RedisCacheConfiguration> initialCacheConfigurations) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations);
    }

    public TTLRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration,
                                Map<String, RedisCacheConfiguration> initialCacheConfigurations, boolean allowInFlightCacheCreation) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations, allowInFlightCacheCreation);
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    /**
     * CacheResolver调用CacheManager的getCache方法后会调用该方法进行装饰，这里我们可以给Cache加上过期时间
     *
     * @param cache
     * @return
     */
    @Override
    protected Cache decorateCache(Cache cache) {
        RedisCache redisCache = (RedisCache) cache;
        RedisCacheConfiguration config = redisCache.getCacheConfiguration().entryTtl(resolveExpiryTime(ttl, timeUnit));
        return super.decorateCache(super.createRedisCache(redisCache.getName(), config));
    }

    private Duration resolveExpiryTime(long timeToLive, TimeUnit timeUnit) {
        return Duration.ofMillis(timeUnit.toMillis(timeToLive));
    }

}

