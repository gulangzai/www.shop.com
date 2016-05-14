package javacommon.base;

import org.apache.ibatis.session.RowBounds;

public class BaseQuery implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3280037286456781087L;

	private int pageNumber;
	
    private int pageSize;

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
    
    
    
}