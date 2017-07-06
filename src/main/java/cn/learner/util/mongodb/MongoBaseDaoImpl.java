/** 
 * Project Name:util 
 * File Name:MongoBaseDaoImpl.java 
 * Package Name:cn.learner.util.mongodb 
 * Date:2017年7月6日上午9:22:12 
 * 
 */

package cn.learner.util.mongodb;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * ClassName:MongoBaseDaoImpl Function: TODO ADD FUNCTION. Date: 2017年7月6日
 * 上午9:22:12
 * 
 * @author baoqi.zhang
 * @version
 * @since JDK 1.7
 * @see
 */
public class MongoBaseDaoImpl<T extends BaseDocument> implements MongoBaseDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	private static Logger log = Logger.getLogger(MongoBaseDaoImpl.class);

	// 实体类类型(由构造方法自动赋值)
	private Class<T> entityClass;
	private final String entity;

	public MongoBaseDaoImpl() {
		this.entityClass = null;
		Class c = this.getClass();
		// System.out.println(c);
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.entityClass = (Class<T>) p[0];
		}
		this.entity = this.entityClass.getSimpleName();
	}

	public void insert(BaseDocument object) throws Exception {
		object.setIsDeleted(0);
		try {
			mongoTemplate.insert(object);
		} catch (Exception e) {
			log.error(e);
		}

	}

	@Override
	public T findOne(BaseCondition condition) throws Exception {

		log.info(condition);
		
		return mongoTemplate.findOne(BaseCondition.toEqualQuery(condition),
				entityClass);
	}

	@Override
	public List<T> findAll(BaseCondition condition) throws Exception {
		List<T> list = null;
		log.info(condition);
		try {
			list = mongoTemplate.find(BaseCondition.toEqualQuery(condition),
					entityClass);
		} catch (Exception e) {
			log.error(e);
		}
		return list;
	}

	@Override
	public void updateFirst(BaseCondition query, BaseCondition update)
			throws Exception {
		log.info(query);
		log.info(update);
		try {
			mongoTemplate.updateFirst(BaseCondition.toEqualQuery(query),
					BaseCondition.toSetUpdate(update), entityClass);
		} catch (Exception e) {
			log.error(e);
		}

	}

	@Override
	public void updateMulti(BaseCondition query, BaseCondition update)
			throws Exception {
		log.info(query);
		log.info(update);
		try {
			mongoTemplate.updateMulti(BaseCondition.toEqualQuery(query),
					BaseCondition.toSetUpdate(update), entityClass);
		} catch (Exception e) {
			log.error(e);
		}

	}

	@Override
	public T get(Serializable id) throws Exception {
		T doc = null;
		try {
			doc = mongoTemplate.findOne(new Query(Criteria.where("uuid").is(id)),
					entityClass);
		} catch(Exception e){
			log.error(e);
		}
		return doc;
	}

	@Override
	public void removeOne(BaseCondition query) throws Exception {

		log.info(query);
		try {
			mongoTemplate.updateFirst(BaseCondition.toEqualQuery(query),
					new Update().set(BaseCondition.ISDELETED, 1),
					entityClass);
		} catch (Exception e){
			log.error(e);
		}
		

	}

	@Override
	public void removeMulti(BaseCondition query) throws Exception {
		
		log.info(query);
		
		try{
			mongoTemplate.updateMulti(BaseCondition.toEqualQuery(query),
					new Update().set(BaseCondition.ISDELETED, 1),
					entityClass);
		} catch(Exception e) {
			log.error(e);
		}
		
	}

}
