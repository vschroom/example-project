package com.chernov.exampleproject.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

@Configuration
@EnableCaching
public class RedisCacheConfig {

    @Value("${cache.default.minutes.ttl:}")
    private long cacheDefaultTtl;
    @Value("${cache.names:}")
    private String cacheNames;

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(RedisSerializer<Object> redisSerializer) {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(cacheDefaultTtl))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer));
    }

    @Bean
    @ConditionalOnMissingBean
    public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer(ObjectMapper objectMapper) {
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder()
                .configure(WRITE_DATES_AS_TIMESTAMPS, false)
                .addModule(new JavaTimeModule())
                .findAndAddModules()
                .build();
    }
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(cacheNames.split(","));
    }
}
