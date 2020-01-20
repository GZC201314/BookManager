package org.bsm.pageModel;
// Generated 2020-1-1 16:12:16 by Hibernate Tools 4.3.5.Final

import java.util.Date;

public class PageUser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3243653850988254719L;
	
	/**
	 * 修改用户使用字段
	 */
	private String oldname;
	
	public String getOldname() {
		return oldname;
	}

	public void setOldname(String oldname) {
		this.oldname = oldname;
	}
	private String ids;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	private int page;
	private int rows;
	
	

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	//查询条件字段
	private Date startcreatetime;
	private Date endcreatetime;
	private Date startmodifytime;
	private Date endmodifytime;
	private String username;
	
	//查询排序字段
	private String sort;
	private String order;
	
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Date getStartcreatetime() {
		return startcreatetime;
	}

	public void setStartcreatetime(Date startcreatetime) {
		this.startcreatetime = startcreatetime;
	}

	public Date getEndcreatetime() {
		return endcreatetime;
	}

	public void setEndcreatetime(Date endcreatetime) {
		this.endcreatetime = endcreatetime;
	}

	public Date getStartmodifytime() {
		return startmodifytime;
	}

	public void setStartmodifytime(Date startmodifytime) {
		this.startmodifytime = startmodifytime;
	}

	public Date getEndmodifytime() {
		return endmodifytime;
	}

	public void setEndmodifytime(Date endmodifytime) {
		this.endmodifytime = endmodifytime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	private String id;
	private String name;
	private String pwd;
	private Date createdatetime;
	private Date lastmodifytime;
	public PageUser() {
	}

	public PageUser(String id) {
		this.id = id;
	}

	public PageUser(String id, String name, String pwd) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getCreatedatetime() {
		return createdatetime;
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	public Date getLastmodifytime() {
		return lastmodifytime;
	}

	public void setLastmodifytime(Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}

}
