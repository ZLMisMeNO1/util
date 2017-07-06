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
 * ClassName:BaseContition Function: TODO ADD FUNCTION. Date: 2017年7月6日
 * 上午11:41:42
 * 
 * @author baoqi.zhang
 * @version
 * @since JDK 1.7
 * @see
 */
public class BaseCondition {
	
	public static final String ISDELETED = "isDeleted";

	private Map<String,Object> queryConditionAndValue ;
	
	private Integer page;

	private Integer row;

	public static BaseCondition getSelf() {
		return new BaseCondition();
	}

	public static BaseCondition put(String param ,Object value) {
		
		BaseCondition condition = getSelf();
		
		if(null == condition.getQueryConditionAndValue() ||
				condition.getQueryConditionAndValue().size() == 0) {
			condition.setQueryConditionAndValue(new HashMap<String,Object>());
		}

		condition.getQueryConditionAndValue().put(param, value);
		
		return condition;
	}
	public static Query map2Query(Map<String, Object> map) {

		BaseCondition condition = getSelf();
		
		condition.setQueryConditionAndValue(map);

		return toEqualQuery(condition);
	}

	public static Query toEqualQuery(BaseCondition condition) {
		Query query = new Query();
		
		Map<String,Object> map = condition.getQueryConditionAndValue();
		for(String key : map.keySet()) {
			query.addCriteria(Criteria.where(key).is(map.get(key)));
		}
		query.addCriteria(Criteria.where(ISDELETED).is(0));
		return query;
	}

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
		sb.append("---> condition -->  ");
		for(String key :this.queryConditionAndValue.keySet()) {
			sb.append(key+"="+this.queryConditionAndValue.get(key)+" ");
		}
		return sb.toString();
	}

	
}
