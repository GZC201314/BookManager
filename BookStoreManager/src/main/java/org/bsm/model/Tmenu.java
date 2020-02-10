package org.bsm.model;
// Generated 2020-2-10 11:19:53 by Hibernate Tools 4.0.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tmenu generated by hbm2java
 */
@Entity
@Table(name = "tmenu", catalog = "bookmanager")
public class Tmenu implements java.io.Serializable {

	private String id;
	private Tmenu tmenu;
	private String text;
	private String iconCls;
	private String url;
	private Set<Tauthorize> tauthorizes = new HashSet<Tauthorize>(0);
	private Set<Tmenu> tmenus = new HashSet<Tmenu>(0);

	public Tmenu() {
	}

	public Tmenu(String id) {
		this.id = id;
	}

	public Tmenu(String id, Tmenu tmenu, String text, String iconCls, String url, Set<Tauthorize> tauthorizes,
			Set<Tmenu> tmenus) {
		this.id = id;
		this.tmenu = tmenu;
		this.text = text;
		this.iconCls = iconCls;
		this.url = url;
		this.tauthorizes = tauthorizes;
		this.tmenus = tmenus;
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
	@JoinColumn(name = "pid")
	public Tmenu getTmenu() {
		return this.tmenu;
	}

	public void setTmenu(Tmenu tmenu) {
		this.tmenu = tmenu;
	}

	@Column(name = "text", length = 100)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "iconCls", length = 50)
	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Column(name = "url", length = 200)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmenu")
	public Set<Tauthorize> getTauthorizes() {
		return this.tauthorizes;
	}

	public void setTauthorizes(Set<Tauthorize> tauthorizes) {
		this.tauthorizes = tauthorizes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmenu")
	public Set<Tmenu> getTmenus() {
		return this.tmenus;
	}

	public void setTmenus(Set<Tmenu> tmenus) {
		this.tmenus = tmenus;
	}

}
