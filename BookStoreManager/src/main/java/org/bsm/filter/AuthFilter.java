package org.bsm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.bsm.util.JWTUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(description = "鉴权过滤器", urlPatterns = { "/AuthFilter" })
public class AuthFilter implements Filter {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(AuthFilter.class.getName());

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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		logger.info("鉴权过滤器!!!!!!!!!");
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getServletPath();
		if (StringUtils.isEmpty(url)) {
			return;
		} else if (url.contains("login") || url.contains("reg")|| url.contains("repair")||url.contains("menuAction")) {//如果是注册和登录以及数据库修复则跳过过滤器
			chain.doFilter(request, response);
		} else {
			// 其他的action需要校验是否有权限访问
			String token = req.getParameter("token");
			String refreshToken = req.getParameter("refreshToken");

			// 请求中的token是否在redis中存在

			ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] { "classpath:spring.xml" });
			RedisTemplate<String, String> redisTemplate = (RedisTemplate<String, String>) ac.getBean("redisTemplate");

			String redisToken = (String) redisTemplate.opsForHash().get("token", refreshToken);
			if (!token.equals(redisToken)) {
				return;
			}
			boolean verifyResult = JWTUtil.verify(token);

			if (!verifyResult) {
				ServerHttpResponse rep = (ServerHttpResponse) response;
				rep.setStatusCode(HttpStatus.OK);
				rep.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

//	            Response res = new Response(1004, "invalid token");  
//	            byte[] responseByte = JSONObject.fromObject(res).toString().getBytes(StandardCharsets.UTF_8);  
//	              
//	            DataBuffer buffer = rep.bufferFactory().wrap(responseByte);  
//	            return rep.writeWith(Flux.just(buffer));  
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
