package org.bsm.action;

import java.io.File;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.bsm.pageModel.Book;
import org.bsm.pageModel.BookQuery;
import org.bsm.pageModel.Json;
import org.bsm.pageModel.PageDataGrid;
import org.bsm.pageModel.Role;
import org.bsm.service.BookServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ModelDriven;

@Action(value = "bookAction")
public class BookAction extends BaseAction implements ModelDriven<BookQuery> {

	private static final Logger logger = LogManager.getLogger(BookAction.class.getName());

	BookQuery bookQuery = new BookQuery();

	@Autowired
	BookServiceI bookService;

	@Override
	public BookQuery getModel() {
		return bookQuery;
	}

	public void getComboboxItem() {
		super.writeJson(bookService.getRoleItem());
	}

	/**
	 * 上传图书封面图像
	 */

	public void uploadBookIcon() {
		logger.info("into the uploadHeadIcon function");
		Json j = new Json();
		try {
			String base64 = bookService.uploadBookIcon(bookQuery);
			if (!StringUtils.isEmpty(base64)) {
				j.setMsg("上传头像成功.");
				j.setSuccess(true);
				j.setObj(base64);
			} else {
				j.setMsg("上传头像失败.");
				j.setSuccess(false);
			}

		} catch (Exception e) {
			j.setMsg(e.getMessage());
			j.setSuccess(false);
			logger.error(e.getMessage());
		}
		super.writeJson(j);
		logger.info("out into the uploadHeadIcon function");
	}

	/**
	 * 修改图书
	 */
	public void edit() {
		logger.info("into the edit Book function");
		Json j = new Json();
		try {
			bookService.update(bookQuery);
			j.setSuccess(true);
			j.setMsg("更新图书信息成功.");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			j.setSuccess(false);
		}
		super.writeJson(j);
		logger.info("out into the edit Book  function....");
	}

	/**
	 * 新增图书信息
	 */
	public void add() {
		logger.info("into the Add Book function");
		Json j = new Json();
		bookService.update(bookQuery);
		j.setSuccess(true);
		j.setMsg("图书信息修改成功.");
		super.writeJson(j);
		logger.info("out into the Add Book  function....");
	}

	/**
	 * 删除图书
	 */
	public void removeBook() {
		logger.info("into the removeBook Role function");
		Json json = new Json();
		try {
			bookService.removeBook(bookQuery);
			json.setMsg("删除图书成功.");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		super.writeJson(json);
		logger.info("out into the removeBook Role function");
	}

	/**
	 * 获取图书列表
	 */
	public void datagrid() {
		logger.info("into the Bookdatagrid function");
		PageDataGrid pageDataGrid = new PageDataGrid();
		try {
			pageDataGrid = bookService.datagrid(bookQuery);
		} catch (Exception e) {
		}
		super.writeJson(pageDataGrid);
		logger.info("out into the Bookdatagrid function");
	}

	public void getBookInfobyIsbn() {
		Json json = new Json();
		if (StringUtils.isEmpty(bookQuery.getIsbn())) {
			json.setSuccess(false);
			json.setMsg("ISBN参数为空.");
		} else {
			Book book = bookService.getBookInfobyIsbn(bookQuery);
			if (StringUtils.isEmpty(book)) {
				json.setSuccess(false);
				json.setMsg("未获取图书信息.");
			} else {
				json.setMsg("成功获取图书信息.");
				json.setSuccess(true);
				json.setObj(book);
			}
		}
		super.writeJson(json);
	}

}
