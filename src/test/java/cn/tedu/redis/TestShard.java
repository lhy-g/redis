package cn.tedu.redis;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class TestShard {
	public static void main(String[] args) {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(200);
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		JedisShardInfo info1 = new JedisShardInfo("192.168.239.128", 7000);
		JedisShardInfo info2 = new JedisShardInfo("192.168.239.128", 7001);
		JedisShardInfo info3 = new JedisShardInfo("192.168.239.128", 7002);
		shards.add(info3);
		shards.add(info1);
		shards.add(info2);
		ShardedJedisPool pool = new ShardedJedisPool(poolConfig, shards);
		ShardedJedis redis = pool.getResource();
		for(int i=0;i<20;i++){
			redis.set("n"+i, i+"");
		}
		String value = null;
		for(int i=0;i<20;i++){
			value = redis.get("n"+i);
			System.out.println(value);
		}
	 
		pool.returnResource(redis);
		pool.close();
	}
}
