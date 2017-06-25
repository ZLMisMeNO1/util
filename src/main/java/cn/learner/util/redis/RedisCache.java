/** 
 * Project Name:Learner 
 * File Name:RedisCache.java 
 * Package Name:cn.osbullshit.learner.java.util 
 * Date:2017年6月7日下午2:41:31 
 * redis缓存应用
 */

package cn.learner.util.redis;

import java.io.IOException;
import java.util.ResourceBundle;

import cn.learner.util.serialize.SerializeUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * ClassName:RedisCache Function: TODO ADD FUNCTION. Date: 2017年6月7日 下午2:41:31
 * 
 * @author baoqi.zhang
 * @version
 * @since JDK 1.7
 * @see
 */
public class RedisCache {

	private static JedisPool pool = null;

	private static String host;

	private static Integer port;

	/**
	 * 构建redis连接池
	 * 
	 * @param ip
	 * @param port
	 * @return JedisPool
	 */
	public static JedisPool getPool() throws IOException {
		if (host == null || host.equals("") || port == null || port == 0) {
			ResourceBundle resourceBundle = java.util.ResourceBundle
					.getBundle("redis");
			host = resourceBundle.getString("host").trim();
			port = Integer.parseInt(resourceBundle.getString("port").trim());
		}

		if (pool == null) {
			JedisPoolConfig config = new JedisPoolConfig();
			// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
			// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
			config.setMaxActive(500);
			// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
			config.setMaxIdle(5);
			// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
			config.setMaxWait(1000 * 100);
			// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
			config.setTestOnBorrow(true);
			pool = new JedisPool(config, host, port);
		}
		return pool;
	}

	/**
	 * 返还到连接池
	 * 
	 * @param pool
	 * @param redis
	 */
	public static void returnResource(JedisPool pool, Jedis redis) {
		if (redis != null) {
			pool.returnResource(redis);
		}
	}

	/**
	 * 存放数据
	 * 
	 * @param key
	 * @return
	 */
	public static void put(String key, Object value) {
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			jedis.set(key.getBytes(), SerializeUtil.serialize(value));
		} catch (JedisConnectionException e) {
			return;
		} catch (Exception e) {
			// 释放redis对象
			e.printStackTrace();
			pool.returnBrokenResource(jedis);
			return;
		} finally {
			// 返还到连接池
			returnResource(pool, jedis);
		}
	}

	/**
	 * 获取数据
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		Object value = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			value = SerializeUtil.unserialize(jedis.get(key.getBytes()));
		} catch (JedisConnectionException e) {
			return value;
		} catch (Exception e) {
			// 释放redis对象
			e.printStackTrace();
			pool.returnBrokenResource(jedis);
		} finally {
			// 返还到连接池
			returnResource(pool, jedis);
		}
		return value;
	}

	/**
	 * 删除数据
	 * 
	 * @param key
	 * @return
	 */
	public static void remove(String key) {
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			jedis.del(key.getBytes());
		} catch (JedisConnectionException e) {
			return;
		} catch (Exception e) {
			// 释放redis对象
			e.printStackTrace();
			pool.returnBrokenResource(jedis);
		} finally {
			// 返还到连接池
			returnResource(pool, jedis);
		}
	}

	public static boolean containsKey(String key) {
		boolean b = false;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			b = jedis.exists(key.getBytes());
		} catch (JedisConnectionException e) {
			return b;
		} catch (Exception e) {
			// 释放redis对象
			e.printStackTrace();
			pool.returnBrokenResource(jedis);
		} finally {
			returnResource(pool, jedis);
		}
		return b;
	}
}
