package org.bsm.service;

import org.bsm.pageModel.AipFaceResult;
import org.bsm.pageModel.BaiduAI;
import org.bsm.pageModel.PageUser;

public interface BaiduAIServiceI {
	
	public String uploadHeadIcon(BaiduAI baiduAI);
	
	public AipFaceResult facelogin(PageUser pageUser);
	
	public AipFaceResult faceReg(PageUser pageUser);
}
