package com.mloine.auth.auths.secutity;


import com.google.common.collect.Sets;
import com.mloine.auth.auths.entity.UserLoginInfo;
import com.mloine.auth.auths.utils.SerializeUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.*;

public class ShiroRedisCache<K, V> implements Cache<K, V> {
	private Logger log = LoggerFactory.getLogger(ShiroRedisCache.class);
	private RedisTemplate<byte[], V> shiroRedisTemplate;
	private String prefix = "shiro:cache:";

	public ShiroRedisCache(RedisTemplate<byte[], V> shiroRedisTemplate) {
		this.shiroRedisTemplate = shiroRedisTemplate;
	}

	public ShiroRedisCache(RedisTemplate<byte[], V> shiroRedisTemplate, String prefix) {
		this(shiroRedisTemplate);
		this.prefix = prefix;
	}

	@Override
	public V get(K key) throws CacheException {
		if (key == null) {
			return null;
		}

		byte[] bkey = getByteKey(key);
		if (log.isDebugEnabled()) {
			log.debug("Get Key: {}", new String(bkey));
		}
		return shiroRedisTemplate.opsForValue().get(bkey);
	}

	@Override
	public V put(K key, V value) throws CacheException {
		if (log.isDebugEnabled()) {
			log.debug("Put Key: {}, value: {}", key, value);
		}

		if (key == null || value == null) {
			return null;
		}

		byte[] bkey = getByteKey(key);
		shiroRedisTemplate.opsForValue().set(bkey, value);
		return value;
	}

	@Override
	public V remove(K key) throws CacheException {
		if (log.isDebugEnabled()) {
			log.debug("Remove Key: {}", key);
		}

		if (key == null) {
			return null;
		}

		byte[] bkey = getByteKey(key);
		ValueOperations<byte[], V> vo = shiroRedisTemplate.opsForValue();
		V value = vo.get(bkey);
		shiroRedisTemplate.delete(bkey);
		return value;
	}

	@Override
	public void clear() throws CacheException {
		shiroRedisTemplate.getConnectionFactory().getConnection().flushDb();
	}

	@Override
	public int size() {
		Long len = shiroRedisTemplate.getConnectionFactory().getConnection().dbSize();
		return len.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keys() {
		byte[] bkey = (prefix + "*").getBytes();
		Set<byte[]> set = shiroRedisTemplate.keys(bkey);
		Set<K> result = Sets.newHashSet();

		if (CollectionUtils.isEmpty(set)) {
			return Collections.emptySet();
		}

		for (byte[] key : set) {
			result.add((K) key);
		}
		return result;
	}

	@Override
	public Collection<V> values() {
		Set<K> keys = keys();
		List<V> values = new ArrayList<>(keys.size());
		for (K k : keys) {
			byte[] bkey = getByteKey(k);
			values.add(shiroRedisTemplate.opsForValue().get(bkey));
		}
		return values;
	}

	private byte[] getByteKey(K key) {
		if (key instanceof String) {
			String preKey = this.prefix + key;
			return preKey.getBytes();
		} else if (key instanceof UserLoginInfo){
			String preKey = this.prefix + ((UserLoginInfo) key).getUserId();
			return preKey.getBytes();
		} else if (key instanceof SimplePrincipalCollection) {
			Object primaryPrincipal = ((SimplePrincipalCollection) key).getPrimaryPrincipal();
			if (primaryPrincipal instanceof UserLoginInfo) {
				String preKey = this.prefix + ((UserLoginInfo) primaryPrincipal).getUserId();
				return preKey.getBytes();
			} else {
				return SerializeUtils.serialize(key);
			}
		} else {
			return SerializeUtils.serialize(key);
		}
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}