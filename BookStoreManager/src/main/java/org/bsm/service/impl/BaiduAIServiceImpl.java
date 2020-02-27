package org.bsm.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bsm.pageModel.BaiduAI;
import org.bsm.pageModel.Tpsbresult;
import org.bsm.pageModel.Words_result;
import org.bsm.service.BaiduAIServiceI;
import org.bsm.util.BaiduAIAuthUtil;
import org.bsm.util.FileUtil;
import org.bsm.util.GeneralBasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

@Service("baiduAIService")
public class BaiduAIServiceImpl implements BaiduAIServiceI {

	@Autowired
	StringRedisTemplate redisTemplate;
	
	@Override
	public String uploadHeadIcon(BaiduAI baiduAI) {
		String result = "";
		if (!StringUtils.isEmpty(baiduAI.getUpload())) {
			// 保存
			try {
				//判断是否本地有access_token
				String access_token = redisTemplate.opsForValue().get("tpsb_access_token");
				if(StringUtils.isEmpty(access_token)) {
					//如果本地没有缓存 2592000
					access_token = BaiduAIAuthUtil.getAuth("1OlEjVz6Zck7h4kdCpSu2GDX", "1gylrnGUjKdUbFSqUyGd54LwUCCUStno");
					redisTemplate.opsForValue().set("tpsb_access_token", access_token, 2592000, TimeUnit.SECONDS);
				}
				String reString = GeneralBasic.generalBasic(baiduAI.getUpload().getCanonicalPath(), access_token);
				Tpsbresult tpsbresult = JSONObject.parseObject(reString, Tpsbresult.class);
				if(!StringUtils.isEmpty(tpsbresult)) {
					List<Words_result> list = tpsbresult.getWords_result();
					for (Words_result words_result : list) {
						result += words_result.getWords();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
}
