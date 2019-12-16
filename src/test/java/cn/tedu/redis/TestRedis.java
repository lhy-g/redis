package cn.tedu.redis;

import redis.clients.jedis.Jedis;

public class TestRedis {
	public static void main(String[] args) {
		//
		Jedis jedis = new Jedis("192.168.239.128", 6379);
		jedis.set("name", "HelloWord");
		String name = jedis.get("name");
		System.out.println(name);
		jedis.close();
		System.out.println("收工");
	}
}
