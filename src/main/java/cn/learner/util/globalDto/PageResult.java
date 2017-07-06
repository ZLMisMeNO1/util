/** 
 * Project Name:Learner 
 * File Name:PageResult.java 
 * Package Name:cn.osbullshit.learner.dto 
 * Date:2017年6月12日下午4:34:25 
 * 
 */  
  
package cn.learner.util.globalDto;  

import java.io.Serializable;
import java.util.List;

/** 
 * ClassName:PageResult 
 * Function: TODO ADD FUNCTION. 
 * Date:     2017年6月12日 下午4:34:25 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class PageResult<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Integer pageSize;
	
	private Integer pageNumber;
	
	private Integer totalPage;

	private Long total;
	
	private List<T> rows;
	

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getTotalPage() {
		double zhi=((double) total/pageSize);
		totalPage = (int) Math.ceil(zhi);
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		
		this.totalPage = totalPage;
	}
	
	
	
}
 