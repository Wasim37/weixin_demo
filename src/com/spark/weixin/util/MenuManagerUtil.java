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
 * 菜单管理器类
 * 
 * @author 
 * @date 2013-10-17
 */
public class MenuManagerUtil {
	
	private static Logger LOGGER = LoggerFactory.getLogger(MenuManagerUtil.class);

	/**
	 * 定义菜单结构
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		
		ClickButton btn11 = new ClickButton();
		btn11.setName("星火招聘");
		btn11.setType("click");
		btn11.setKey("spark");

		ViewButton btn12 = new ViewButton();
		btn12.setName("index页面");
		btn12.setType("view");
		btn12.setUrl("http://112.74.79.169/index.jsp");

		ClickButton btn13 = new ClickButton();
		btn13.setName("个人信息");
		btn13.setType("click");
		btn13.setKey("userInfo");

		ViewButton btn21 = new ViewButton();
		btn21.setName("芥末堆");
		btn21.setType("view");
		btn21.setUrl("http://www.jiemodui.com/");

		ViewButton btn22 = new ViewButton();
		btn22.setName("多知网");
		btn22.setType("view");
		btn22.setUrl("http://www.duozhi.com/");

		ViewButton btn23 = new ViewButton();
		btn23.setName("辅导圈");
		btn23.setType("view");
		btn23.setUrl("http://fudaoquan.com/");

		ViewButton btn31 = new ViewButton();
		btn31.setName("星火题库网");
		btn31.setType("view");
		btn31.setUrl("http://tiku.xiaojiaoyu100.com/module/login_panel/login.html");

		ViewButton btn32 = new ViewButton();
		btn32.setName("星空博客");
		btn32.setType("view");
		btn32.setUrl("http://www.wangxin123.com");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("测试菜单");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("行业资讯");
		mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("相关网站");
		mainBtn3.setSub_button(new Button[] { btn31, btn32 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}

	public static void main(String[] args) {
		
		// 第三方用户唯一凭证
		String appId = "wx77680c50bdb783ee";
		
		// 第三方用户唯一凭证密钥
		String appSecret = "626b89345ce9758f6c4e983379f1cec9";

		// 调用接口获取凭证
		Token token = CommonUtil.getToken(appId, appSecret);

		if (null != token) {
			// 创建菜单
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());

			// 判断菜单创建结果
			if (result){
				LOGGER.info("菜单创建成功！");
			}
			else{
				LOGGER.info("菜单创建失败！");
			}
		}
	}
}
