package org.bsm.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bsm.dao.BaseDaoI;
import org.bsm.model.Tbook;
import org.bsm.model.TbookId;
import org.bsm.model.Ttag;
import org.bsm.pageModel.Book;
import org.bsm.pageModel.BookQuery;
import org.bsm.pageModel.Combobox;
import org.bsm.pageModel.PageDataGrid;
import org.bsm.service.BookServiceI;
import org.bsm.util.APIUtils;
import org.bsm.util.DataUtils;
import org.bsm.util.FileUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

@Service(value = "bookService")
public class BookServiceImpl implements BookServiceI {


	@Autowired
	private BaseDaoI<Tbook> bookDAO;

	@Autowired
	private BaseDaoI<Ttag> tagDAO;

	/**
	 * 从URL地址返回的json结果中抓取图书
	 * 
	 * @param url 目标地址
	 * @return 抓取到的图书对象
	 * @throws IOException
	 */
	private Book grabBook(String url) throws IOException {
		String json = DataUtils.getJsonStringFromURL(url);
		Book book = new Book();
		if (!StringUtils.isEmpty(json)) {
			JSONObject jsonObject = JSONObject.parseObject(json);
			String jsonarray = jsonObject.getString("data");
			List<Book> list = JSONObject.parseArray(jsonarray, Book.class);
			if (null != list && list.size() > 0) {
				book = list.get(0);
			}
		}
		String clc = book.getClc();
		if (!StringUtils.isEmpty(clc)) {
			String htmlString = DataUtils.getHtmlStringFromURL("http://ztflh.xhma.com/search?w=" + clc);
			Document document = Jsoup.parse(htmlString);
			Elements element = document.getElementsByAttributeValue("class", "category-title text-overflow");
			while ( element.isEmpty()) {
				clc = clc.substring(0, clc.length() - 1);
				if (StringUtils.isEmpty(clc)) {
					break;
				}
				htmlString = DataUtils.getHtmlStringFromURL("http://ztflh.xhma.com/search?w=" + clc);
				document = Jsoup.parse(htmlString);
				element = document.getElementsByAttributeValue("class", "category-title text-overflow");
			}
			String text = "";
			if (element.isEmpty()) {
				text = book.getClc();
			} else {
				Element element2 = element.get(0);
				text = element2.ownText();
			}
			String taghql = "from Ttag where id=:id";
			Map<String, Object> tagParams = new HashMap<>();
			tagParams.put("id", clc);
			Ttag t = new Ttag(clc, text);
			book.setClc(clc);
			if (StringUtils.isEmpty(tagDAO.get(taghql, tagParams))) {
				tagDAO.save(t);
			}
			Tbook tbook = new Tbook();
			BeanUtils.copyProperties(book, tbook);
			tbook.setTtag(t);
			TbookId tbookId = new TbookId(book.getCode(), book.getIsbn());
			tbook.setId(tbookId);
			String bookhql = "from Tbook where code=:code and isbn=:isbn";
			Map<String, Object> bookParams = new HashMap<>();
			bookParams.put("code", book.getCode());
			bookParams.put("isbn", book.getIsbn());
			if (StringUtils.isEmpty(bookDAO.get(bookhql, bookParams))) {
				bookDAO.save(tbook);
			}
		}
		return book;
	}

	@Override
	public Book getBookInfobyIsbn(BookQuery bookQuery) {
		String url = APIUtils.createFindBooksURL(bookQuery.getIsbn());
		try {
			return grabBook(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PageDataGrid datagrid(BookQuery bookQuery) {
		// 添加过滤条件
		String condition = " where 1=1 ";
		Map<String, Object> params = new HashMap<>();

		if (!StringUtils.isEmpty(bookQuery.getBookname())) {
			condition += " and name like :name";
			params.put("name", "%%" + bookQuery.getBookname().trim() + "%%");
		}

		// 添加排序方式
		String order = "";
		if (bookQuery.getSort() != null && bookQuery.getOrder() != null) {
			order = " order by " + bookQuery.getSort() + " " + bookQuery.getOrder();
		}

		String hql = "from Tbook" + condition + order;

		Long count = bookDAO.count("select count(*) " + hql, params);
		List<Tbook> lt = bookDAO.find(hql, params, bookQuery.getPage(), bookQuery.getRows());
		List<Book> bookList = new ArrayList<Book>();
		if (!CollectionUtils.isEmpty(lt)) {
			for (Tbook tbook : lt) {
				Book book = new Book();
				BeanUtils.copyProperties(tbook, book);
				book.setCode(tbook.getId().getCode());
				book.setIsbn(tbook.getId().getIsbn());
				book.setClcText(tbook.getTtag().getName());
				book.setClc(tbook.getTtag().getId());
				bookList.add(book);
			}
		}
		PageDataGrid pageDataGrid = new PageDataGrid();
		pageDataGrid.setRows(bookList);
		pageDataGrid.setTotal(count);
		return pageDataGrid;
	}

	@Override
	public void removeBook(BookQuery bookQuery) {
		if (!StringUtils.isEmpty(bookQuery.getIds())) {
			String ids = bookQuery.getIds();
			String hql = "delete from Tbook t where t.isbn in (" + ids + ")";
			bookDAO.executeHql(hql);
		}
			
	}

	@Override
	public List<Combobox> getRoleItem() {
		String hql = "from Ttag";
		List<Combobox> comboboxs = new ArrayList<Combobox>();
		List<Ttag> lists = tagDAO.find(hql);
		for (Ttag ttag : lists) {
			Combobox combobox = new Combobox();
			combobox.setId(ttag.getId());
			combobox.setText(ttag.getName());
			comboboxs.add(combobox);
		}
		return comboboxs;
	}

	@Override
	public void update(BookQuery bookQuery) {
		if (!StringUtils.isEmpty(bookQuery)) {
			
			String hql = "from Tbook where code=:code and isbn=:isbn";
			Map<String, Object> params = new HashMap<>();
			params.put("code", bookQuery.getCode());
			params.put("isbn", bookQuery.getIsbn());
			Tbook tbook = bookDAO.get(hql, params);
			if(!StringUtils.isEmpty(tbook)) {
				if(StringUtils.isEmpty(bookQuery.getUploadbookImg())) {
					BeanUtils.copyProperties(bookQuery, tbook,"image");
				}else {
					BeanUtils.copyProperties(bookQuery, tbook,"image");
					tbook.setImage(FileUtil.fileToBase64(bookQuery.getUploadbookImg()));
				}
				tbook.setTtag(new Ttag(bookQuery.getClc(), bookQuery.getClcText()));
				bookDAO.saveOrUpdate(tbook);
			}
		}
	}

	/**
	 * 
	 */
	@Override
	public String uploadBookIcon(BookQuery bookQuery) {
		String base64 ="";
		if (!StringUtils.isEmpty(bookQuery.getUploadbookImg())) {
			base64 = FileUtil.fileToBase64(bookQuery.getUploadbookImg());
		}
		return base64;
	}

}
