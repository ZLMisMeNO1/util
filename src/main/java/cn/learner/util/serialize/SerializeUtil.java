/** 
 * Project Name:Learner 
 * File Name:SerializeUtil.java 
 * Package Name:cn.osbullshit.learner.java.util 
 * Date:2017年6月7日下午2:40:13 
 * 序列化工具
 */  
  
package cn.learner.util.serialize;  

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

/** 
 * ClassName:SerializeUtil 
 * Function: TODO ADD FUNCTION. 
 * Date:     2017年6月7日 下午2:40:13 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class SerializeUtil {

	private static final Logger log = Logger.getLogger(SerializeUtil.class);
	
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			log.error("序列化失败");
			e.printStackTrace();
		}
		return null;
	}

	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			log.error("反序列化失败");
			e.printStackTrace();
		}
		return null;
	}
}
 