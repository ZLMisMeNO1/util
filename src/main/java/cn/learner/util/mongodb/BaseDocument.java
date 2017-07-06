/** 
 * Project Name:util 
 * File Name:BaseDocument.java 
 * Package Name:cn.learner.util.mongodb 
 * Date:2017年7月6日上午9:16:11 
 * 
 */

package cn.learner.util.mongodb;

import java.io.Serializable;
import java.sql.Timestamp;

import cn.learner.util.uuid.UUIDUtil;

/**
 * ClassName:BaseDocument Function: TODO ADD FUNCTION. Date: 2017年7月6日 上午9:16:11
 * 
 * @author baoqi.zhang
 * @version
 * @since JDK 1.7
 * @see
 */
public class BaseDocument implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 是否删除
	 */
	private Integer isDeleted = 0;

	private String uuid = UUIDUtil.uuid();
	
	private Timestamp createTime = new Timestamp(System.currentTimeMillis());
	
	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
}
