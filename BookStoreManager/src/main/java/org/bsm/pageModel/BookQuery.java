package org.bsm.pageModel;

import java.io.File;

public class BookQuery {
	
    private File uploadbookImg;//定义一个File ,变量名要与jsp中的input标签的name一致
    private String uploadContentType;//上传文件的mimeType类型
    private String uploadFileName;//上传文件的名称
	

	public File getUploadbookImg() {
		return uploadbookImg;
	}

	public void setUploadbookImg(File uploadbookImg) {
		this.uploadbookImg = uploadbookImg;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}	
	
	
	private String code;
	private String Isbn;
	private String clc;
	private String clcText;
	private String splitIsbn;
	private String name;
	private String englishName;
	private String title;
	private String seriesName;
	private String copiesCount;
	private String cip;
	private String author;
	private String introduction;
	private String content;
	private String publisher;
	private String publishingTime;
	private String publishingAddress;
	private String edition;
	private String print;
	private String score;
	private String translate;
	private String editor;
	private String illustrator;
	private String pageCount;
	private String folio;
	private String size;
	private String weight;
	private String price;

	public BookQuery(String isbn) {
		super();
		Isbn = isbn;
	}

	public String getIsbn() {
		return Isbn;
	}

	public void setIsbn(String isbn) {
		Isbn = isbn;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getClc() {
		return clc;
	}

	public void setClc(String clc) {
		this.clc = clc;
	}

	public String getClcText() {
		return clcText;
	}

	public void setClcText(String clcText) {
		this.clcText = clcText;
	}

	public String getSplitIsbn() {
		return splitIsbn;
	}

	public void setSplitIsbn(String splitIsbn) {
		this.splitIsbn = splitIsbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getCopiesCount() {
		return copiesCount;
	}

	public void setCopiesCount(String copiesCount) {
		this.copiesCount = copiesCount;
	}

	public String getCip() {
		return cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishingTime() {
		return publishingTime;
	}

	public void setPublishingTime(String publishingTime) {
		this.publishingTime = publishingTime;
	}

	public String getPublishingAddress() {
		return publishingAddress;
	}

	public void setPublishingAddress(String publishingAddress) {
		this.publishingAddress = publishingAddress;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getPrint() {
		return print;
	}

	public void setPrint(String print) {
		this.print = print;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getTranslate() {
		return translate;
	}

	public void setTranslate(String translate) {
		this.translate = translate;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getIllustrator() {
		return illustrator;
	}

	public void setIllustrator(String illustrator) {
		this.illustrator = illustrator;
	}

	public String getPageCount() {
		return pageCount;
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	private String ids;
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	/**
	 * 查询排序字段
	 */
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
	/**
	 * 分页数据
	 */
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
	
	private String bookname;


	public String getBookname() {
		return bookname;
	}

	public BookQuery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
}
