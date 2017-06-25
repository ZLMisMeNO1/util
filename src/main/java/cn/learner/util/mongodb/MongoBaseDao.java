package cn.learner.util.mongodb;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: MongoBaseDao 
 * @Description:  
 * @author baoqi.zhang
 * @date 2017年6月25日 下午5:03:40 
 * 注意：本内容仅限于learner内部技术人员使用，禁止外泄以及用于其他的商业目的
 */
public interface MongoBaseDao<T> {

	/**
	 * 
	 * @Title: insert   添加数据
	 * @Description: 
	 * @param: @param object
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws   
	 * @author baoqi.zhang
	 */
    public void insert(T object) throws Exception;    
    /**
    * 
    * @Title: findOne   根据条件查找  
    * @Description: 
    * @param: @param params
    * @param: @return
    * @param: @throws Exception      
    * @return: T      
    * @throws   
    * @author baoqi.zhang
    */
    public T findOne(Map<String,Object> params)  throws Exception;    
    /**
     * 
     * @Title: findAll   查找所有  
     * @Description: 
     * @param: @param params
     * @param: @param collectionName
     * @param: @return
     * @param: @throws Exception      
     * @return: List<T>      
     * @throws   
     * @author baoqi.zhang
     */
    public List<T> findAll(Map<String,Object> params,String collectionName) throws Exception;    
    /**
     * 
     * @Title: update   修改  
     * @Description: 
     * @param: @param params
     * @param: @param collectionName
     * @param: @throws Exception      
     * @return: void      
     * @throws   
     * @author baoqi.zhang
     */
    public void update(Map<String,Object> params,String collectionName) throws Exception;   
    //创建集合  
//    public void createCollection(String collectionName) throws Exception;  
    /**
     * 
     * @Title: remove   根据条件删除  
     * @Description: 
     * @param: @param params
     * @param: @param collectionName
     * @param: @throws Exception      
     * @return: void      
     * @throws   
     * @author baoqi.zhang
     */
    public void remove(Map<String,Object> params,String collectionName) throws Exception;  
}
