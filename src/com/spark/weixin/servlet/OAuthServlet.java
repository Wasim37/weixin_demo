package com.spark.weixin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.spark.weixin.pojo.SNSUserInfo;
import com.spark.weixin.pojo.WeixinOauth2Token;
import com.spark.weixin.util.AdvancedUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ��Ȩ��Ļص�������
 * 
 * @author 
 * @date 2013-11-12
 */
public class OAuthServlet extends HttpServlet {
	
	private static Logger log = LoggerFactory.getLogger(OAuthServlet.class);
	
	private static final long serialVersionUID = -1847238807216447030L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// �û�ͬ����Ȩ���ܻ�ȡ��code
		/*String code = request.getParameter("code");*/
		String code = "041fbwhl2cdM8J0vP1il2Enthl2fbwhE";
		log.info("code============" + code);
		
		// �û�ͬ����Ȩ
		if (!"authdeny".equals(code)) {
			// ��ȡ��ҳ��Ȩaccess_token
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken("wx77680c50bdb783ee", "626b89345ce9758f6c4e983379f1cec9", code);
			
			log.info("weixinOauth2Token============" + weixinOauth2Token);
			// ��ҳ��Ȩ�ӿڷ���ƾ֤
			String accessToken = weixinOauth2Token.getAccessToken();
			// �û���ʶ
			String openId = weixinOauth2Token.getOpenId();
			// ��ȡ�û���Ϣ
			SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);

			// ����Ҫ���ݵĲ���
			request.setAttribute("snsUserInfo", snsUserInfo);
		}
		// ��ת��index.jsp
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
