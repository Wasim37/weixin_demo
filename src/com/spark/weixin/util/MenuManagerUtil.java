package com.spark.weixin.util;

import com.spark.weixin.menu.Button;
import com.spark.weixin.menu.ClickButton;
import com.spark.weixin.menu.ComplexButton;
import com.spark.weixin.menu.Menu;
import com.spark.weixin.menu.ViewButton;
import com.spark.weixin.pojo.Token;
import com.spark.weixin.util.CommonUtil;
import com.spark.weixin.util.MenuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * �˵���������
 * 
 * @author 
 * @date 2013-10-17
 */
public class MenuManagerUtil {
	
	private static Logger LOGGER = LoggerFactory.getLogger(MenuManagerUtil.class);

	/**
	 * ����˵��ṹ
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		
		ClickButton btn11 = new ClickButton();
		btn11.setName("�ǻ���Ƹ");
		btn11.setType("click");
		btn11.setKey("spark");

		ViewButton btn12 = new ViewButton();
		btn12.setName("indexҳ��");
		btn12.setType("view");
		btn12.setUrl("http://112.74.79.169/index.jsp");

		ClickButton btn13 = new ClickButton();
		btn13.setName("������Ϣ");
		btn13.setType("click");
		btn13.setKey("userInfo");

		ViewButton btn21 = new ViewButton();
		btn21.setName("��ĩ��");
		btn21.setType("view");
		btn21.setUrl("http://www.jiemodui.com/");

		ViewButton btn22 = new ViewButton();
		btn22.setName("��֪��");
		btn22.setType("view");
		btn22.setUrl("http://www.duozhi.com/");

		ViewButton btn23 = new ViewButton();
		btn23.setName("����Ȧ");
		btn23.setType("view");
		btn23.setUrl("http://fudaoquan.com/");

		ViewButton btn31 = new ViewButton();
		btn31.setName("�ǻ������");
		btn31.setType("view");
		btn31.setUrl("http://tiku.xiaojiaoyu100.com/module/login_panel/login.html");

		ViewButton btn32 = new ViewButton();
		btn32.setName("�ǿղ���");
		btn32.setType("view");
		btn32.setUrl("http://www.wangxin123.com");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("���Բ˵�");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("��ҵ��Ѷ");
		mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("�����վ");
		mainBtn3.setSub_button(new Button[] { btn31, btn32 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}

	public static void main(String[] args) {
		
		// �������û�Ψһƾ֤
		String appId = "wx77680c50bdb783ee";
		
		// �������û�Ψһƾ֤��Կ
		String appSecret = "626b89345ce9758f6c4e983379f1cec9";

		// ���ýӿڻ�ȡƾ֤
		Token token = CommonUtil.getToken(appId, appSecret);

		if (null != token) {
			// �����˵�
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());

			// �жϲ˵��������
			if (result){
				LOGGER.info("�˵������ɹ���");
			}
			else{
				LOGGER.info("�˵�����ʧ�ܣ�");
			}
		}
	}
}
