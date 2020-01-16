package org.bsm.action;



import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.bsm.model.Tuser;
import org.bsm.pageModel.AuthResult;
import org.bsm.pageModel.Json;
import org.bsm.pageModel.PageDataGrid;
import org.bsm.pageModel.PageUser;
import org.bsm.service.UserServiceI;
import org.bsm.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value="userAction")
public class UserAction extends BaseAction implements ModelDriven<PageUser> {
	private static final Logger logger = LogManager.getLogger(UserAction.class.getName());
	
	private PageUser pageUser = new PageUser();
	
	@Autowired
	private UserServiceI userServiceI;
    @Autowired  
    StringRedisTemplate redisTemplate;
	@Override
	public PageUser getModel() {
		return pageUser;
	}
	/**
	 * 注册
	 */
	public void reg() {
		logger.info("into the reg function");
		Json j = new Json();
		try {
			userServiceI.save(pageUser);
			j.setSuccess(true);
			j.setMsg("注册成功.");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
		logger.info("out into the reg function");
	}
	/**
	 * 获取用户列表
	 */
	public void datagrid() {
		logger.info("into the datagrid function");
		PageDataGrid pageDataGrid = new PageDataGrid();
		try {
			pageDataGrid = userServiceI.datagrid(pageUser);
		} catch (Exception e) {
		}
		super.writeJson(pageDataGrid);
		logger.info("out into the datagrid function");
	}
	/**
	 * 登录
	 */
	public void login() {
		logger.info("into the login function");
		Json j = new Json();
		try {
			Tuser tuser = userServiceI.login(pageUser);
			if(tuser != null) {
				
	            //生成token  
	            String token = JWTUtil.generateToken(tuser.getName());  
	              
	            //生成refreshToken  
	            String refreshToken = UUID.randomUUID().toString();
				
	            //数据放入redis  
	            redisTemplate.opsForHash().put(refreshToken, "token", token);  
	            redisTemplate.opsForHash().put(refreshToken, "username", tuser.getName()); 
				
	            //设置token的过期时间  
	            redisTemplate.expire(refreshToken, JWTUtil.REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);  
	            j.setObj(new AuthResult(token, refreshToken));
	            j.setMsg("登录成功.");
				j.setSuccess(true);

			}else {
				j.setMsg("登录失败.");
			}
				
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			j.setSuccess(false);

		}
		super.writeJson(j);
		logger.info("out into the login function");
	}

}
