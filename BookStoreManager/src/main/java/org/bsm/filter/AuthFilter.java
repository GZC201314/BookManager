package org.bsm.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.bsm.pageModel.ErrorMsg;
import org.bsm.pageModel.Json;
import org.bsm.util.JWTUtil;
import org.bsm.util.ParameterRequestWrapper;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(description = "鉴权过滤器", urlPatterns = { "/AuthFilter" })
public class AuthFilter implements Filter {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(AuthFilter.class.getName());

	private static ApplicationContext ctx = null;

	/**
	 * Default constructor.
	 */
	public AuthFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response1, FilterChain chain)
			throws IOException, ServletException {
		// place your code here
		logger.info("鉴权过滤器!!!!!!!!!");
		request.setCharacterEncoding("utf-8");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse response = (HttpServletResponse) response1;
		HashMap<String, Object> params = new HashMap(req.getParameterMap());
		String url = req.getServletPath();
		String refreshToken = "";
		String token = "";
		String userName = "";
		if (StringUtils.isEmpty(url)) {
			return;
			// TODO
		} else if (url.contains("check") || url.contains("login") || url.contains("reg") || url.contains("repair")
				|| url.contains("validateName")) {// 如果是注册和登录以及数据库修复则跳过过滤器
			chain.doFilter(request, response);
		} else {

			// 其他的action需要校验是否有权限访问
			Cookie[] cookies = req.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if ("token".equals(cookie.getName())) {
						token = cookie.getValue();
						params.put("token", token);
					}
					if ("refreshToken".equals(cookie.getName())) {
						refreshToken = cookie.getValue();
						params.put("refreshToken", refreshToken);
					}
					if ("userName".equals(cookie.getName())) {
						userName = cookie.getValue();
						URLDecoder urlDecoder = new URLDecoder();
						userName = urlDecoder.decode(userName, "UTF-8");
						params.put("username", userName);
					}
				}
			}
			
			ParameterRequestWrapper wrapRequest=new ParameterRequestWrapper(req,params);    
			request=wrapRequest;
			// 请求中的token是否在redis中存在,获取cookies里边的Token
			ServletContext sc = req.getSession().getServletContext();
			ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
			RedisTemplate<String, String> redisTemplate = (RedisTemplate<String, String>) ctx.getBean("redisTemplate");

			String redisToken = (String) redisTemplate.opsForHash().get(refreshToken, "token");
			ErrorMsg errorMsg = new ErrorMsg();
			if (!token.equals(redisToken)) {
				response.setContentType("application/json;charset=UTF-8");
	            response.setCharacterEncoding("UTF-8");
	            response.sendError(402);
	            Json eJson = new Json();
	            errorMsg.setErrorCode(402);
	            errorMsg.setErrorMsg("token is error!!!");
	            eJson.setObj(errorMsg);
	            String json = JSON.toJSONStringWithDateFormat(eJson, "yyyy-MM-dd HH:mm:ss");
	            OutputStream out = response.getOutputStream();
	            out.write(json.getBytes("UTF-8"));
	            out.flush();
	            return;
			}
			boolean verifyResult = JWTUtil.verify(token);
			if (!verifyResult) {
				response.setContentType("application/json;charset=UTF-8");
	            response.setCharacterEncoding("UTF-8");
	            response.sendError(403);
	            Json eJson = new Json();
	            errorMsg.setErrorCode(403);
	            errorMsg.setErrorMsg("token is out time!!!");
	            eJson.setObj(errorMsg);
	            String json = JSON.toJSONStringWithDateFormat(eJson, "yyyy-MM-dd HH:mm:ss");
	            OutputStream out = response.getOutputStream();
	            out.write(json.getBytes("UTF-8"));
	            out.flush();
	            return;
			}

			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
