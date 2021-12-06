package org.bsm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.ocr.AipOcr;
import org.bsm.pageModel.*;
import org.bsm.service.BaiduAIServiceI;
import org.bsm.service.UserServiceI;
import org.bsm.util.BaiduAipInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

@Service("baiduAIService")
public class BaiduAIServiceImpl implements BaiduAIServiceI {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    UserServiceI userServiceI;

    @Override
    public String uploadHeadIcon(BaiduAI baiduAI) {
        String result = "";


        if (!StringUtils.isEmpty(baiduAI.getUpload())) {
            // 保存
            try {
                // 初始化一个AipOcr
                AipOcr client = BaiduAipInstance.getOcrInstance();

                if (!StringUtils.isEmpty(client)) {
                    // 调用接口
                    String path = baiduAI.getUpload().getCanonicalPath();
                    HashMap<String, String> map = new HashMap<>();
                    org.json.JSONObject res = client.basicGeneral(path, map);
                    Tpsbresult tpsbresult = JSONObject.parseObject(res.toString(2), Tpsbresult.class);
                    if (!StringUtils.isEmpty(tpsbresult)) {
                        List<Words_result> list = tpsbresult.getWords_result();
                        for (Words_result words_result : list) {
                            result += words_result.getWords();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }


    @Override
    public AipFaceResult facelogin(PageUser pageUser) {
        AipFace aipFace = BaiduAipInstance.getFaceInstance();
        org.json.JSONObject resultJson = aipFace.search(pageUser.getBase(), "BASE64", "test", null);
        AipFaceResult result = JSONObject.parseObject(resultJson.toString(), AipFaceResult.class);
        return result;
    }


    @Override
    public AipFaceResult faceReg(PageUser pageUser) {
        AipFace aipFace = BaiduAipInstance.getFaceInstance();
        //获取用户的人脸识别信息
        PageUser reUser = userServiceI.userInfo(pageUser);
        reUser.setPwd("");
        reUser.setOldname(reUser.getName());
        reUser.setIsFaceValid(1);
        userServiceI.update(reUser);
        //获取登录的用户名
        org.json.JSONObject resultJson = aipFace.addUser(pageUser.getBase(), "BASE64", "test", pageUser.getUsername(), null);
        AipFaceResult result = JSONObject.parseObject(resultJson.toString(), AipFaceResult.class);
        return result;
    }

}
