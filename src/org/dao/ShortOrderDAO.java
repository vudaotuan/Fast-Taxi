package org.dao;

import java.util.Set;

import org.AppConfig;
import org.apache.log4j.Logger;
import org.model.ShortOrder;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

public class ShortOrderDAO {

	static Logger logger = Logger.getLogger(ShortOrderDAO.class);

	private JedisPool jedisPool;
	private int db;
	private int expireTime;

	public ShortOrderDAO() {
		try {
			AppConfig appConfig = AppConfig.getInstance();
			String host = appConfig.getProperty("RedisIndex.URL");
			int port = Integer.parseInt(appConfig
					.getProperty("RedisIndex.port"));
			int timeout = Integer.parseInt(appConfig
					.getProperty("RedisIndex.timeout"));
			db = Integer.parseInt(appConfig.getProperty("RedisIndex.db"));
			expireTime = Integer.parseInt(appConfig
					.getProperty("RedisIndex.expire"));

			jedisPool = new JedisPool(new JedisPoolConfig(), host, port,
					timeout);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	public ShortOrder userRequest(int user_id, int user_lon, int user_lat) {
		ShortOrder shortOrder = null;
		try {
			Jedis jedis = jedisPool.getResource();
			jedis.select(db);
			
			
			Set<String> keys = jedis.keys("*_" + user_id);
			
			if (keys.size() == 1){
				for (String key : keys) {
					
					shortOrder = new ShortOrder(jedis.get(key));
					
					shortOrder.setLon_customer(user_lon);
					shortOrder.setLat_customer(user_lat);
					
					jedis.set(key, shortOrder.toString());
					
					if (shortOrder.is_going_closed()){
						jedis.expire(key, expireTime);
					}

					jedis.disconnect();

					jedisPool.returnResource(jedis);
				}
				
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return shortOrder;
	}
	
	public ShortOrder taxiRequest(int taxi_id, int taxi_lon, int taxi_lat) {
		ShortOrder shortOrder = null;
		try {
			Jedis jedis = jedisPool.getResource();
			jedis.select(db);
			
			Set<String> keys = jedis.keys(taxi_id + "_*");
			
			if (keys.size() == 1){
				for (String key : keys) {
					
					shortOrder = new ShortOrder(jedis.get(key));
					
					shortOrder.setLat_taxi(taxi_lat);
					shortOrder.setLon_taxi(taxi_lon);
					
					jedis.set(key, shortOrder.toString());
					
					if (shortOrder.is_going_closed()){
						jedis.expire(key, expireTime);
					}

					jedis.disconnect();

					jedisPool.returnResource(jedis);
				}
				
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return shortOrder;
	}
	
	public void insertShortOrder(ShortOrder shortOrder) {
		try {
			Jedis jedis = jedisPool.getResource();
			jedis.select(db);
			
			String key = shortOrder.getTaxi_id() + "_" + shortOrder.getCustomer_id();
			
			jedis.set(key, shortOrder.toString());
			
			if (shortOrder.is_going_closed()){
				jedis.expire(key, expireTime);
			}

			jedis.disconnect();

			jedisPool.returnResource(jedis);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
