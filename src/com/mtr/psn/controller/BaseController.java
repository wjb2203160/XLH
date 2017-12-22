package com.mtr.psn.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

import org.apache.log4j.Logger;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mtr.psn.common.Constants;
import com.mtr.psn.model.user.User;

/**
 * @author lingd
 */
public class BaseController {
	public Logger logger = Logger.getLogger(BaseController.class);

	private User currentUser;

	public User getCurrentUser() {
		if (null == this.currentUser) {
			// 获得request对象
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			// 获取session；false：没有不创建，返回空 true：没有就创建一个session
			HttpSession session = request.getSession(false);
			if (session != null) {
				currentUser = (User) session.getAttribute(Constants.SESSION_USER);
			} else {
				currentUser = null;
			}
		}
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	/*
	 * 日期国际化 注解的意思是初始化绑定
	 */
	@InitBinder
	public void InitBinder(WebDataBinder dataBinder) {
		// 第一个参数是 需要的类型，第二个参数是 属性编辑类型
		dataBinder.registerCustomEditor(Data.class, new PropertyEditorSupport() {
			public void setAsTest(String value) {
				try {
					setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					setValue(null);
				}
			}

			public String getAsTest() {
				return new SimpleDateFormat("yyyy-MM-dd").format((Date) getValue());
			}
		});
	}
}
