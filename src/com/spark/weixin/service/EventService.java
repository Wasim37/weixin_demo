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
 * �¼��������������
 * 
 * @author 
 * @date 2013-10-17
 */
public class EventService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(EventService.class);

	/**
	 * ����΢�ŷ���������: �¼���������
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processEventRequest(Map<String, String> requestMap) throws Exception{
		
		String fromUserName = requestMap.get("FromUserName"); // ���ͷ��ʺ�
		String toUserName = requestMap.get("ToUserName"); // ������΢�ź�
		String eventType = requestMap.get("Event");
		
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		
		String respXml = null;
		
		// ����
		if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
			textMessage.setContent("����ܾ��ˣ���л��ע�����ںţ������ǿ������΢�Ų��Ժ�СQ�����ˡ�\n\n�ظ����ǻ���Ƹ�� �����ǻ���Ƹ\n\n�ظ���������Ϣ�� ����΢�ź���Ϣ");
			// ����Ϣ����ת����xml
			respXml = MessageUtil.messageToXml(textMessage);
		}
		// ȡ������
		else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
			
		}
		// �Զ���˵�����¼�
		else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
			// �¼�KEYֵ���봴���˵�ʱ��keyֵ��Ӧ
			String eventKey = requestMap.get("EventKey");
			// ����keyֵ�ж��û�����İ�ť
			if (eventKey.equals("spark")) {
				Article article = new Article();
				article.setTitle("�ǻ���Ƹ");
				article.setDescription("�ǻ����������ʽ������2008�꣬��������ʱ���ѷ�չ��Ϊ�˹���ǰ���K12���⸨��������һ·�����ǻ𱣳��Ÿ��ٷ�չ��ԭ�����Ŷ������ѧ�Ļ��򣬹�˾��ʼ�˾�Ϊ80���Ŷ���90��Ϊ����\n\n���ŵ��Ĵλ����������ĵ������ǻ��ٴ��𺽽��л���������ҵ");
				article.setPicUrl("https://www.lgstatic.com/i/image/M00/51/86/CgqKkVe4DzuAQeNzAAHEH-UBGyI591.jpg");
				article.setUrl("https://www.lagou.com/gongsi/125157.html");
				List<Article> articleList = new ArrayList<Article>();
				articleList.add(article);
				// ����ͼ����Ϣ
				NewsMessage newsMessage = new NewsMessage();
				newsMessage.setToUserName(fromUserName);
				newsMessage.setFromUserName(toUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsMessage.setArticleCount(articleList.size());
				newsMessage.setArticles(articleList);
				respXml = MessageUtil.messageToXml(newsMessage);
			} else if (eventKey.equals("userInfo")) {
				
				// ��ȡ�ӿڷ���ƾ֤
				String accessToken = CommonUtil.getToken("wx77680c50bdb783ee", "626b89345ce9758f6c4e983379f1cec9").getAccessToken();
				
				WeixinUserInfo user = AdvancedUtil.getUserInfo(accessToken, "oEoyzwty6rUHaC8PE9qZqDoUJ-Fs");
				String str = "";
				str = str + "OpenID��" + user.getOpenId() +"\n\n";
				str = str + "��ע״̬��" + user.getSubscribe() +"\n\n";
				str = str + "��עʱ�䣺" + user.getSubscribeTime() +"\n\n";
				str = str + "�ǳƣ�" + user.getNickname() +"\n\n";
				str = str + "�Ա�" + user.getSex() +"\n\n";
				str = str + "���ң�" + user.getCountry() +"\n\n";
				str = str + "ʡ�ݣ�" + user.getProvince() +"\n\n";
				str = str + "���У�" + user.getCity() +"\n\n";
				str = str + "���ԣ�" + user.getLanguage() +"\n\n";
				str = str + "ͷ��" + user.getHeadImgUrl() +"\n\n";
				
				textMessage.setContent("���ã�����΢����Ϣ����\n\n" + str);
				
				// ����Ϣ����ת����xml
				respXml = MessageUtil.messageToXml(textMessage);
			} else {
				textMessage.setContent("�˵���ò�Ʒ�����δ֪���󡣡���");
				respXml = MessageUtil.messageToXml(textMessage);
			}
		}
		return respXml;
	}
}
