package org.bsm.model;
// Generated 2020-1-30 22:28:22 by Hibernate Tools 4.0.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Tauthorize generated by hbm2java
 */
@Entity
@Table(name = "tauthorize", catalog = "bookmanager")
public class Tauthorize implements java.io.Serializable {

	private String id;
	private Tmenu tmenu;
	private Trole trole;

	public Tauthorize() {
	}

	public Tauthorize(String id) {
		this.id = id;
	}

	public Tauthorize(String id, Tmenu tmenu, Trole trole) {
		this.id = id;
		this.tmenu = tmenu;
		this.trole = trole;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authorizepage")
	public Tmenu getTmenu() {
		return this.tmenu;
	}

	public void setTmenu(Tmenu tmenu) {
		this.tmenu = tmenu;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleid")
	public Trole getTrole() {
		return this.trole;
	}

	public void setTrole(Trole trole) {
		this.trole = trole;
	}

}