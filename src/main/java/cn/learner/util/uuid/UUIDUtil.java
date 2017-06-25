/** 
 * Project Name:Learner 
 * File Name:UUIDUtil.java 
 * Package Name:cn.osbullshit.learner.java.util 
 * Date:2017年6月8日下午7:17:43 
 * 
 */  
  
package cn.learner.util.uuid;  

import java.util.UUID;

/** 
 * ClassName:UUIDUtil 
 * Function: TODO ADD FUNCTION. 
 * Date:     2017年6月8日 下午7:17:43 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class UUIDUtil {

	/**
	 * 
	 * @Title: uuid   返回uuid
	 * @Description: 
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 * @author baoqi.zhang
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}
}
 