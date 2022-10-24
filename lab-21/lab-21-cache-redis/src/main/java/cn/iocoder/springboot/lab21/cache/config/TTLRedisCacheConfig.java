package cn.iocoder.springboot.lab21.cache.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author ludepeng
 * @date 2022-10-21 22:34
 */
@EnableCaching
@Configuration
public class TTLRedisCacheConfig {

    private static final String KEY_SEPERATOR = ":";

    @Bean(name = "ttlRedisCacheManager")
    public TTLRedisCacheManager ttlRedisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisSerializer<String> keySerializer = new StringRedisSerializer();
        RedisSerializer<Object> valueSerializer = new GenericJackson2JsonRedisSerializer();

        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig();
        cacheConfig = cacheConfig.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer));
        cacheConfig = cacheConfig.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer));
        cacheConfig = cacheConfig.computePrefixWith(key -> "ttl" + KEY_SEPERATOR + key + KEY_SEPERATOR);
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        return new TTLRedisCacheManager(redisCacheWriter, cacheConfig);
    }

    @Bean(name = "ttlCacheResolver")
    public TTLCacheResolver ttlCacheResolver(TTLRedisCacheManager ttlRedisCacheManager) {
        return new TTLCacheResolver(ttlRedisCacheManager);
    }
}

