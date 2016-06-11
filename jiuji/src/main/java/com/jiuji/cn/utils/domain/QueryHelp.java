package com.jiuji.cn.utils.domain;

import java.util.ArrayList;
import java.util.List;


public class QueryHelp {
	
	private int currentPage; // ��ǰҳ
	private int pageSize; // ÿҳ��ʾ������

	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	private String fromClause; // FROM�Ӿ�
	private String whereClause = ""; // Where�Ӿ�
	private String orderByClause = ""; // OrderBy�Ӿ�
	
	
	
	private List<Object> parameters = new ArrayList<Object>(); // �����б�
	
	public QueryHelp(Class clazz, String alias) {
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}

	public QueryHelp(int currentPage) {
		// TODO Auto-generated constructor stub
		this.currentPage=currentPage;
	}

	public QueryHelp() {
		// TODO Auto-generated constructor stub
	}

	public List<Object> getParameters() {
		// TODO Auto-generated method stub
		return parameters;
	}
	
	/**
	 * ��ȡ���ɵ����ڲ�ѯ�����б��HQL���
	 * 
	 * @return
	 */
	public String getListQueryHql() {
		return fromClause + whereClause + orderByClause;
	}
	
	/**
	 * ��ȡ���ɵ����ڲ�ѯ�ܼ�¼����HQL���
	 * 
	 * @return
	 */
	public String getCountQueryHql() {
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}
	
	
}
