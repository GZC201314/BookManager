package org.bsm.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.bsm.pageModel.BaiduAI;
import org.bsm.pageModel.Json;
import org.bsm.pageModel.Menu;
import org.bsm.service.BaiduAIServiceI;
import org.bsm.service.MenuServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ModelDriven;

@Action(value = "baiduAIAction")
public class BaiduAIAction extends BaseAction implements ModelDriven<BaiduAI> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaiduAIAction.class);

	BaiduAI baiduAI = new BaiduAI();
	
	@Override
	public BaiduAI getModel() {
		return baiduAI;
	}
	
	@Autowired
	private BaiduAIServiceI baiduAIServiceI;

	/**
	 * 上传文字识别图像
	 */

	public void uploadImg() {
		logger.info("into the uploadImg function");
		Json j = new Json();
		try {
			String result = baiduAIServiceI.uploadHeadIcon(baiduAI);
			j.setMsg("图片识别成功");
			j.setObj(result);
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			j.setSuccess(false);
			j.setMsg("图片识别失败");
			logger.error(e.getMessage());
		}
		super.writeJson(j);
		logger.info("out into the uploadImg function");
	}

}
