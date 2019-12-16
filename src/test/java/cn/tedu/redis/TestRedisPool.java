package cn.tedu.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 池化Redis
 * 
 * @author Administrator
 *
 */
public class TestRedisPool {
	public static void main(String[] args) {
		JedisPoolConfig poolConfig  = new JedisPoolConfig();
		poolConfig.setMaxTotal(200);
		JedisPool pool = new JedisPool(poolConfig, "192.168.239.128",6379);
		Jedis jdeis = pool.getResource();
		String name = jdeis.get("name");
		System.out.println(name);
		pool.returnResource(jdeis);
		pool.close();
	}
}
