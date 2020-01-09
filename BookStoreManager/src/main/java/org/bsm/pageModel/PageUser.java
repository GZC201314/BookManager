package org.bsm.pageModel;
// Generated 2020-1-1 16:12:16 by Hibernate Tools 4.3.5.Final

public class PageUser implements java.io.Serializable {

	private String id;
	private String name;
	private String pwd;

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

}
