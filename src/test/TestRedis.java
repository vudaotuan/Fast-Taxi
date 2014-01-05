package test;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestRedis {
	public static void main (String args[]){
		
		JedisPool jedisPool = new JedisPool(new JedisPoolConfig(),
				"123.30.236.142", 6379, 60000);
		
		Jedis jedis = jedisPool.getResource();
		jedis.select(12);
		
		System.out.println(jedis.smembers(""));
		
		System.out.println(jedis.dbSize());
	}
}
