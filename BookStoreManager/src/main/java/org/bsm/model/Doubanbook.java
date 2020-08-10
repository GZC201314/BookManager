package org.bsm.model;
// Generated 2020-7-19 21:32:47 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Doubanbook generated by hbm2java
 */
@Entity
@Table(name = "doubanbook", catalog = "bookmanager")
public class Doubanbook implements java.io.Serializable {

	private String isbn;
	private String name;
	private String englishName;
	private String title;
	private String seriesName;
	private String author;
	private String introduction;
	private String publisher;
	private String publishingTime;
	private String edition;
	private String score;
	private String translate;
	private String editor;
	private String folio;
	private String size;
	private String weight;
	private String price;
	private String image;

	public Doubanbook() {
	}

	public Doubanbook(String isbn) {
		this.isbn = isbn;
	}

	public Doubanbook(String isbn, String name, String englishName, String title, String seriesName, String author,
			String introduction, String publisher, String publishingTime, String edition, String score,
			String translate, String editor, String folio, String size, String weight, String price, String image) {
		this.isbn = isbn;
		this.name = name;
		this.englishName = englishName;
		this.title = title;
		this.seriesName = seriesName;
		this.author = author;
		this.introduction = introduction;
		this.publisher = publisher;
		this.publishingTime = publishingTime;
		this.edition = edition;
		this.score = score;
		this.translate = translate;
		this.editor = editor;
		this.folio = folio;
		this.size = size;
		this.weight = weight;
		this.price = price;
		this.image = image;
	}

	@Id

	@Column(name = "isbn", unique = true, nullable = false, length = 32)
	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Column(name = "name", length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "englishName", length = 128)
	public String getEnglishName() {
		return this.englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	@Column(name = "title", length = 128)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "seriesName", length = 128)
	public String getSeriesName() {
		return this.seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	@Column(name = "author", length = 128)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "introduction", length = 65535)
	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Column(name = "publisher", length = 128)
	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Column(name = "publishingTime", length = 128)
	public String getPublishingTime() {
		return this.publishingTime;
	}

	public void setPublishingTime(String publishingTime) {
		this.publishingTime = publishingTime;
	}

	@Column(name = "edition", length = 128)
	public String getEdition() {
		return this.edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	@Column(name = "score", length = 128)
	public String getScore() {
		return this.score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Column(name = "translate", length = 128)
	public String getTranslate() {
		return this.translate;
	}

	public void setTranslate(String translate) {
		this.translate = translate;
	}

	@Column(name = "editor", length = 128)
	public String getEditor() {
		return this.editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	@Column(name = "folio", length = 32)
	public String getFolio() {
		return this.folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@Column(name = "size", length = 32)
	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Column(name = "weight", length = 32)
	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@Column(name = "price", length = 32)
	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Column(name = "image", length = 128)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}