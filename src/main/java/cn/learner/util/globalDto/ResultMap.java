/** 
 * Project Name:Learner 
 * File Name:ResultMap.java 
 * Package Name:cn.osbullshit.learner.java.dto 
 * Date:2017年6月7日下午2:09:20 
 * 
 */

package cn.learner.util.globalDto;

import java.io.Serializable;

/**
 * ClassName:ResultMap 应用层返回格式定义 Function: TODO ADD FUNCTION. Date: 2017年6月7日
 * 下午2:09:20
 * 
 * @author baoqi.zhang
 * @version
 * @since JDK 1.7
 * @see
 */
public class ResultMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer state;

	private String message;

	private Object data;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


}
