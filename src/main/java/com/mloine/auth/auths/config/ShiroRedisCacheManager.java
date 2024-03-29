package com.mloine.auth.auths.config;

import com.mloine.auth.auths.secutity.ShiroRedisCache;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

public class ShiroRedisCacheManager extends AbstractCacheManager {

	private RedisTemplate<byte[], byte[]> shiroRedisTemplate;

	public ShiroRedisCacheManager(RedisTemplate<byte[], byte[]> shiroRedisTemplate) {
		this.shiroRedisTemplate = shiroRedisTemplate;
	}

	@Override
	protected Cache<byte[], byte[]> createCache(String name) throws CacheException {
		return new ShiroRedisCache<> (shiroRedisTemplate, name);
	}
}
