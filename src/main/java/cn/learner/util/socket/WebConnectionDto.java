/** 
 * Project Name:util 
 * File Name:WebConnectionDto.java 
 * Package Name:cn.learner.util.socket 
 * Date:2017年7月5日上午10:24:52 
 * 
 */  
  
package cn.learner.util.socket;  

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;


/** 
 * ClassName:WebConnectionDto H5通过EventSource返回服务器响应实体Bean
 * Function: TODO ADD FUNCTION. 
 * Date:     2017年7月5日 上午10:24:52 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public final class WebConnectionDto implements Serializable{

	private static Logger log = Logger.getLogger(WebConnectionDto.class);
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private String id ;
	
	/**
	 * 时间名称
	 */
	private String event;

	/**
	 * 浏览器请求时间 （单位:毫秒）
	 */
	private Integer retry;
	
	/**
	 * 返回值
	 */
	private Map<String,Object> data = new HashMap<String,Object>();
	
	public WebConnectionDto() {
		super();
		this.id = sdf.format(new Date());
		this.event = "load";
		this.retry = 5000;
	}
	/**
	 * 
	 * Creates a new instance of WebConnectionDto. 
	 * 
	 * @param event 自定义事件名称
	 */
	public WebConnectionDto(String event) {
		super();
		this.id = sdf.format(new Date());
		this.event = event;
		this.retry = 5000;
	}
	public WebConnectionDto(String id, String event,Integer retry) {
		super();
		this.id = id;
		this.event = event;
		this.retry = retry;
	}
	public void addData(String key,Object data) {
		if( null == data)
			return;
		this.data.put(key, data);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id:"+ this.id + "\n");
		sb.append("retry:"+ this.retry + "\n");
		sb.append("event:"+ this.event + "\n");
		sb.append("data:" + JSONObject.toJSON(this.data) + "\n");
		log.info("to brower message :" + sb.toString());
		return sb.toString();
	}

}
 