package cn.secure.web.front;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;


import com.alibaba.fastjson.JSON;



import cn.secure.service.BlogHomeService;
import cn.secure.util.BaseController;

import cn.secure.util.JsonResult;

@Controller
@RequestMapping("/index")

public class BlogHomeController extends BaseController {
	@Autowired
	private BlogHomeService blogHomeService;

	@RequestMapping("qianxinLogin")
	@ResponseBody
	public String qianxinLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JsonResult jr = new JsonResult();
		String csrf_token = "";
		csrf_token = blogHomeService.qianxinLogin();
		jr.setResult("csrf_token", csrf_token);
		String r = JSON.toJSONString(jr);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(r);
		return null;
	}

}
