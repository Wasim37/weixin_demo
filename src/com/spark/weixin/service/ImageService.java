package com.spark.weixin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spark.weixin.message.resp.Article;
import com.spark.weixin.message.resp.NewsMessage;
import com.spark.weixin.message.resp.TextMessage;
import com.spark.weixin.pojo.WeixinUserInfo;
import com.spark.weixin.util.AdvancedUtil;
import com.spark.weixin.util.CommonUtil;
import com.spark.weixin.util.MessageUtil;

/**
 * 图片请求服务类
 * 
 * @author 
 * @date 2013-10-17
 */
public class ImageService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ImageService.class);
	
	public static String processImageRequest(Map<String, String> requestMap) throws Exception {
		
		String fromUserName = requestMap.get("FromUserName"); // 发送方帐号
		String toUserName = requestMap.get("ToUserName"); // 开发者微信号

		String respXml = null;
		Article article = new Article();
		article.setTitle("图片试题匹配信息");
		article.setDescription("（2008.武汉）如图，在直角坐标系中，点A，B分别在x轴，y轴上，点A的坐标为（-1，0），∠ABO=30°，线段PQ的端点P从点O出发，沿△OBA的边按O→B→A→O运动一周，同时另一端点Q随之在x轴的非负半轴上运动。\n\n如果PQ=3，那么当点P运动一周时，点Q运动的总路程为多少？");
		article.setPicUrl("http://7xvfir.com1.z0.glb.clouddn.com/%E5%BE%AE%E4%BF%A1/%E5%BE%AE%E4%BF%A1.jpg");
		article.setUrl("http://tiku.xiaojiaoyu100.com/module/login_panel/login.html");
		List<Article> articleList = new ArrayList<Article>();
		articleList.add(article);
		
		// 创建图文消息
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		respXml = MessageUtil.messageToXml(newsMessage);
		
		return respXml;
	}
}
