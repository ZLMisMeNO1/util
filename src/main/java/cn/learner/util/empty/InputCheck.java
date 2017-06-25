package cn.learner.util.empty;

import java.util.Map;

/**
 * 
 * @ClassName: InputCheck 检查参数是否为空
 * @Description:  
 * @author baoqi.zhang
 * @date 2017年6月25日 下午4:53:58 
 * 注意：本内容仅限于osbullshit内部技术人员使用，禁止外泄以及用于其他的商业目的
 */
public class InputCheck {

	/**
	 * 
	 * @Title: isEmpty  checkStr为空或长度为0时，返回true，否则返回false
	 * @Description: 
	 * @param: @param checkStr
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 * @author baoqi.zhang
	 */
	public static Boolean isEmpty(String checkStr) {
		if(null != checkStr && checkStr.length() != 0) 
			return false;
		return true;
	}
	/**
	 * 
	 * @Title: isEmpty  当null值或键集合长度为0时，返回true,否则返回false
	 * @Description: 
	 * @param: @param checkMap
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 * @author baoqi.zhang
	 */
	public static Boolean isEmpty(Map<?,?> checkMap) {
		if(null != checkMap && checkMap.keySet().size() != 0) 
			return false;
		return true;
	}
}
