/** 
 * Project Name:Learner 
 * File Name:BaseContition.java 
 * Package Name:cn.osbullshit.learner.test 
 * Date:2017年7月6日上午11:41:42 
 * 
 */

package cn.learner.util.mongodb;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * ClassName:BaseContition Function: TODO 表达式
 * Date: 2017年7月6日
 * 上午11:41:42
 * 
 * @author baoqi.zhang
 * @version
 * @since JDK 1.7
 * @see
 */
public class BaseCondition {
	
	/**
	 * 逻辑删除字段
	 */
	public static final String ISDELETED = "isDeleted";

	/**
	 * 查询条件
	 */
	private Map<String,Object> queryConditionAndValue ;
	
	/**
	 * 页码
	 */
	private Integer page = 1;

	/**
	 * 条数
	 */
	private Integer row = 15;

	public static BaseCondition getSelf() {
		return new BaseCondition();
	}

	/**
	 * 
	 * put:添加条件
	 * 
	 * @author baoqi.zhang 
	 * @param param
	 * @param value
	 * @return 
	 * @since JDK 1.7
	 */
	public static BaseCondition put(String param ,Object value) {
		
		BaseCondition condition = getSelf();
		
		if(null == condition.getQueryConditionAndValue() ||
				condition.getQueryConditionAndValue().size() == 0) {
			condition.setQueryConditionAndValue(new HashMap<String,Object>());
		}

		condition.getQueryConditionAndValue().put(param, value);
		
		return condition;
	}
	
	/**
	 * 
	 * map2Query:将map转为表达式
	 * 
	 * @author baoqi.zhang 
	 * @param map
	 * @return 
	 * @since JDK 1.7
	 */
	public static Query map2Query(Map<String, Object> map) {

		BaseCondition condition = getSelf();
		
		condition.setQueryConditionAndValue(map);

		return toEqualQuery(condition);
	}

	/**
	 * 
	 * toEqualQuery:转换=查询条件
	 * 
	 * @author baoqi.zhang 
	 * @param condition
	 * @return 
	 * @since JDK 1.7
	 */
	public static Query toEqualQuery(BaseCondition condition) {
		Query query = new Query();
		
		Map<String,Object> map = condition.getQueryConditionAndValue();
		if(null != map && map.size() > 0) {
			for(String key : map.keySet()) {
				query.addCriteria(Criteria.where(key).is(map.get(key)));
			}
		}
		query.addCriteria(Criteria.where(ISDELETED).is(0));
		return query;
	}
	/**
	 * 
	 * toEmptyQuery:空条件
	 * 
	 * @author baoqi.zhang 
	 * @return 
	 * @since JDK 1.7
	 */
	public static Query toEmptyQuery(){
		Query query = new Query();
		query.addCriteria(Criteria.where(ISDELETED).is(0));
		return query;
	}
	
	/**
	 * 
	 * toPageEqualQueryByNormal:根据skip 和 limit 分页
	 * 
	 * @author baoqi.zhang 
	 * @param condition
	 * @return 
	 * @since JDK 1.7
	 */
	public static Query toPageEqualQueryByNormal(BaseCondition condition) {
		int rows = condition.getRow();
		int pageNumber = condition.getPage();
		Query query = toEqualQuery(condition);
		query.skip(rows * (pageNumber - 1) ).limit(rows);
		
		return query;
	}
	/**
	 * 
	 * toPageEqualQueryByTimeMills:分页查询数据  根据时间毫秒值
	 * 
	 * @author baoqi.zhang 
	 * @param condition
	 * @param timeMills
	 * @return 
	 * @since JDK 1.7
	 */
	public static Query toPageEqualQueryByTimeMills(BaseCondition condition,Long timeMills) {
		int rows = condition.getRow();
		Query query = toEqualQuery(condition);
		query.addCriteria(Criteria.where("createTime").gte(timeMills)).limit(rows);
		
		return query;
	}
	/**
	 * 
	 * toSetUpdate:转换更新表达式  $set
	 * 
	 * @author baoqi.zhang 
	 * @param condition
	 * @return 
	 * @since JDK 1.7
	 */
	public static Update toSetUpdate(BaseCondition condition) {
		
		Update update = new Update();
		
		Map<String,Object> map = condition.getQueryConditionAndValue();
		for(String key : map.keySet()) {
			update.set(key,map.get(key));
		}
		return update;
	}

	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Map<String, Object> getQueryConditionAndValue() {
		
		return queryConditionAndValue;
	}

	public void setQueryConditionAndValue(Map<String, Object> queryConditionAndValue) {
		this.queryConditionAndValue = queryConditionAndValue;
	}

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" condition -->  ");
		for(String key :this.queryConditionAndValue.keySet()) {
			sb.append(key+"="+this.queryConditionAndValue.get(key)+" ");
		}
		return sb.toString();
	}

	
}
