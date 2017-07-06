package cn.learner.util.mongodb;

import java.io.Serializable;
import java.util.List;

import cn.learner.util.globalDto.PageResult;

/**
 * 
 * @ClassName: MongoBaseDao
 * @Description:
 * @author baoqi.zhang
 * @date 2017年6月25日 下午5:03:40 注意：本内容仅限于learner内部技术人员使用，禁止外泄以及用于其他的商业目的
 */
public interface MongoBaseDao<T extends BaseDocument> {

	/**
	 * 
	 * @Title: insert 添加数据
	 * @Description:
	 * @param: @param object
	 * @param: @throws Exception
	 * @return: void
	 * @throws
	 * @author baoqi.zhang
	 */
	void insert(T object) throws Exception;

	/**
	 * 
	 * @Title: findOne 根据条件查找
	 * @Description:
	 * @param: @param params
	 * @param: @return
	 * @param: @throws Exception
	 * @return: T
	 * @throws
	 * @author baoqi.zhang
	 */
	T findOne(BaseCondition condition) throws Exception;

	/**
	 * 
	 * @Title: findAll 查找所有
	 * @Description:
	 * @param: @param params
	 * @param: @param collectionName
	 * @param: @return
	 * @param: @throws Exception
	 * @return: List<T>
	 * @throws
	 * @author baoqi.zhang
	 */
	List<T> findAll(BaseCondition condition) throws Exception;

	/**
	 * 
	 * updateFirst:修改单个
	 * 
	 * @author baoqi.zhang
	 * @param query
	 * @param update
	 * @throws Exception
	 * @since JDK 1.7
	 */
	void updateFirst(BaseCondition  query,BaseCondition update)
			throws Exception;

	/**
	 * 
	 * updateMulti:修改多个
	 * 
	 * @author baoqi.zhang 
	 * @param query
	 * @param update
	 * @throws Exception 
	 * @since JDK 1.7
	 */
	void updateMulti(BaseCondition query, BaseCondition update)
			throws Exception;

	// 创建集合
	// public void createCollection(String collectionName) throws Exception;
	/**
	 * 
	 * @Title: remove 根据条件单个
	 * @Description:
	 * @param: @param params
	 * @param: @param collectionName
	 * @param: @throws Exception
	 * @return: void
	 * @throws
	 * @author baoqi.zhang
	 */
	void removeOne(BaseCondition condition) throws Exception;

	/**
	 * 
	 * removeMulti:删除多个
	 * 
	 * @author baoqi.zhang
	 * @param params
	 * @throws Exception
	 * @since JDK 1.7
	 */
	void removeMulti(BaseCondition condition) throws Exception;

	/**
	 * 
	 * get:根据id获取
	 * 
	 * @author baoqi.zhang
	 * @param id
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	T get(Serializable id) throws Exception;
	
	/**
	 * 
	 * listPageByNormal:根据条件  配合 skip 和 limit
	 * 
	 * @author baoqi.zhang 
	 * @param condition
	 * @return
	 * @throws Exception 
	 * @since JDK 1.7
	 */
	PageResult<T> listPageByNormal(BaseCondition condition) throws Exception;

	/**
	 * 
	 * listPageByTime:根据条件 和给定的时间毫秒值 配合limit使用
	 * 
	 * @author baoqi.zhang 
	 * @param condition
	 * @param timeMillis
	 * @return
	 * @throws Exception 
	 * @since JDK 1.7
	 */
	PageResult<T> listPageByTime(BaseCondition condition,Long timeMillis) throws Exception;
}
