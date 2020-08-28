/**  
 * Project Name		batch  
 * File Name		BaseController.java  
 * Package Name		cn.secure.util
 * Date				2018年5月25日下午2:49:41  
 * Copyright (c) 2018, cert@cnic.cn All Rights Reserved.  
 */  
package cn.secure.util;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import cn.secure.service.BlogHomeService;

/**
 * @Description	业务接口调用类
 * @ClassName	BaseController
 * @author		Administrator
 * @date		2018年5月25日 下午2:54:01
 * @version		V1.0
 * @since		JDK 1.8
 */
public class BaseController extends AbstractController {

	@Autowired
	protected ServletContext servletContext;





	@Resource
	protected BlogHomeService blogHomeService;
	

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("BaseController...");
		return super.handleRequest(request, response);
	}

}
