/** 
 * Project Name:util 
 * File Name:WebConnection.java 
 * Package Name:cn.learner.util.socket 
 * Date:2017年7月5日下午1:19:22 
 * 
 */  
  
package cn.learner.util.socket;  

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/** 
 * ClassName:WebConnection 向浏览器提供信息 eventSource
 * Function: TODO ADD FUNCTION. 
 * Date:     2017年7月5日 下午1:19:22 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class WebConnection {

	private static Logger log = Logger.getLogger(WebConnection.class);
	
	public static final void message(HttpServletResponse response ,
			WebConnectionDto dto) {
		response.setHeader("Content-Type", "text/event-stream");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = null;
		try {
			out =  response.getWriter();
			out.println(dto.toString());
			out.flush();
		} catch(Exception e){
			log.error("获取PrintWriter失败"+e);
		} finally {
			if(null != out)
				out.close();
		}
	}
	
}
 