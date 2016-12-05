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
 * 文本请求服务类
 * 
 * @author 
 * @date 2013-10-17
 */
public class TextService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(TextService.class);
	
	public static String processTextRequest(Map<String, String> requestMap) throws Exception{
		
		String respXml = null;
		
		String fromUserName = requestMap.get("FromUserName"); // 发送方帐号
		String toUserName = requestMap.get("ToUserName"); // 开发者微信号
		LOGGER.info("fromUserName:" + fromUserName);
		LOGGER.info("toUserName:" + toUserName);
		
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

		String content = requestMap.get("Content").trim();
		if (content.equals("星火招聘")) {
			Article article = new Article();
			article.setTitle("星火招聘");
			article.setDescription("星火教育集团正式成立于2008年，仅用五年时间已发展成为了国内前五的K12课外辅导机构，一路走来星火保持着高速发展的原因是团队年轻好学的基因，公司创始人均为80后，团队以90后为主。\n\n随着第四次互联网革命的到来，星火再次起航进行互联网领域创业");
			article.setPicUrl("https://www.lgstatic.com/i/image/M00/51/86/CgqKkVe4DzuAQeNzAAHEH-UBGyI591.jpg");
			article.setUrl("https://www.lagou.com/gongsi/125157.html");
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
		} else if (content.equals("个人信息")){
			// 获取接口访问凭证
			String accessToken = CommonUtil.getToken("wx77680c50bdb783ee", "626b89345ce9758f6c4e983379f1cec9").getAccessToken();
			WeixinUserInfo user = AdvancedUtil.getUserInfo(accessToken, fromUserName);
			String str = "";
			str = str + "OpenID：" + user.getOpenId() +"\n\n";
			str = str + "关注状态：" + user.getSubscribe() +"\n\n";
			str = str + "关注时间：" + user.getSubscribeTime() +"\n\n";
			str = str + "昵称：" + user.getNickname() +"\n\n";
			str = str + "性别：" + user.getSex() +"\n\n";
			str = str + "国家：" + user.getCountry() +"\n\n";
			str = str + "省份：" + user.getProvince() +"\n\n";
			str = str + "城市：" + user.getCity() +"\n\n";
			str = str + "语言：" + user.getLanguage() +"\n\n";
			str = str + "头像：" + user.getHeadImgUrl() +"\n\n";
			
			textMessage.setContent("您好，您的微信信息如下\n\n" + str);
			// 将消息对象转换成xml
			respXml = MessageUtil.messageToXml(textMessage);
		} else {
			textMessage.setContent("您发送的是文本消息！");
			respXml = MessageUtil.messageToXml(textMessage);
		}
		return respXml;
	}
}
