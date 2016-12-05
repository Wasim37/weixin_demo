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
 * 核心服务类
 * 
 * @author 
 * @date 2013-10-17
 */
public class CoreService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CoreService.class);
	
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			
			// 事件推送
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				respXml = EventService.processEventRequest(requestMap);
			}
			// 文本消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				textMessage.setContent("您发送的是文本消息！");
				respXml = MessageUtil.messageToXml(textMessage);
				LOGGER.info("-------------" + respXml);
				
				respXml = TextService.processTextRequest(requestMap);
				LOGGER.info("++++++++++++++" + respXml);
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respXml = ImageService.processImageRequest(requestMap);
			}
			// 语音消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				textMessage.setContent("您发送的是语音消息！后期调用百度接口进行语音分析");
				respXml = MessageUtil.messageToXml(textMessage);
			}
			// 视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				textMessage.setContent("您发送的是视频消息！");
				respXml = MessageUtil.messageToXml(textMessage);
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				textMessage.setContent("您发送的是地理位置消息！");
				respXml = MessageUtil.messageToXml(textMessage);
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				textMessage.setContent("您发送的是链接消息！");
				respXml = MessageUtil.messageToXml(textMessage);
			}
			// 当用户发消息时
			else {
				textMessage.setContent("= = ，请通过菜单使用导航服务！");
				respXml = MessageUtil.messageToXml(textMessage);				
			}
		} catch (Exception e) {
			LOGGER.info("CoreService============error:" + e.getMessage(), e);
			e.printStackTrace();
		}
		return respXml;
	}
}
