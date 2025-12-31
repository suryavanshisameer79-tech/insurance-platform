package com.maximus.insurance.customer_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
// Creates Redis connection + template
public class RedisConfig {

    @Bean   // Spring now knows: “Create a RedisTemplate object and manage its lifecycle.”
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        // RedisConnectionFactory factory: Spring will automatically inject the existing Redis connection factory that it has already created.

        RedisTemplate<String, Object> template = new RedisTemplate<>();

        template.setConnectionFactory(connectionFactory);
        //Tell RedisTemplate how to connect to Redis
        // You attach the connection factory to the template.
        //Without this line, RedisTemplate cannot talk to Redis.

        // Key will be stored as String in Redis
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        // Value as JSON using modern serializer
        GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();

        template.setValueSerializer(jsonSerializer);
        template.setHashValueSerializer(jsonSerializer);

        template.afterPropertiesSet();

        return template;
        // Returns the bean
        //Spring now stores and manages this RedisTemplate.
    }
}
