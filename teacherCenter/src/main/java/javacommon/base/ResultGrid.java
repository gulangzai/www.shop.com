/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2011
 */

package javacommon.base;
import java.util.*;
/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class ResultGrid<T> {
	private static final long serialVersionUID = 5454155825314635342L;


	private List<T> rows;
	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> value) {
		this.rows = value;
	}
	
	private Integer total;
	public Integer getTotal(){
		return total;
	}
	
	/** 总页数 */
	public void setTotal(Integer value)
	{
		this.total=value;
	}
	
	private Integer page;
	public Integer getPage(){
		return page;
	}
	/** 当前页数 */
	public void setPage(Integer value)
	{
		this.page=value;
	}
	
	private Integer records;
	public Integer getRecords(){
		return records;
	}
	/** 总记录数 */
	public void setRecords(Integer value)
	{
		this.records=value;
	}
}

