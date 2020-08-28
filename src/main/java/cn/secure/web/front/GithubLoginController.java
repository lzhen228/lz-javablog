package cn.secure.web.front;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.secure.util.BaseController;
import cn.secure.util.CommonUtil;
import cn.secure.util.JsonResult;

/**
 * <p>
 * Github 登录 前置控制器
 *
 * @author java997.com
 * @since 2019-05-25
 */
@Controller
@RequestMapping("/account")
public class GithubLoginController extends BaseController {
//  localhost回调
//	final String client_id = "7135f14d37cfed92845d";
//	final String client_secret = "83ddddd99a67759f140baf98470b51475187139a";
//  eightythousand回调
	final String client_id = "64466896abdc4ee6f609";
	final String client_secret = "b478cf4981561b035112f1f9eb14f21ae4baf0a6";

	@RequestMapping(value = "/callback", method = RequestMethod.GET)
	@ResponseBody
	public String githubCallback(@RequestParam("code") String code, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("==>code:" + code);
		// Step2：通过 Authorization Code 获取 AccessToken
		String githubAccessTokenResult = CommonUtil.sendGet("https://github.com/login/oauth/access_token",
				"client_id=" + client_id + "&client_secret=" + client_secret + "&code=" + code);
		JsonResult jr = new JsonResult();
		/**
		 * 以 & 为分割字符分割 result
		 */
		String[] githubResultList = githubAccessTokenResult.split("&");
		List<String> params = new ArrayList<>();
//
//        // paramMap 是分割后得到的参数对, 比说 access_token=ad5f4as6gfads4as98fg
		for (String paramMap : githubResultList) {
			if (!"scope".equals(paramMap.split("=")[0])) {
				// 再以 = 为分割字符分割, 并加到 params 中
				params.add(paramMap.split("=")[1]);
			}
		}

//        //此时 params.get(0) 为 access_token;  params.get(1) 为 token_type
//
//        // Step2：通过 access_token 获取用户信息
		String githubInfoResult = CommonUtil.sendGet("https://api.github.com/user", "access_token=" + params.get(0));
		// 登录完成 获登录的用户信息
		request.getSession().setAttribute("userInfo", githubInfoResult);
		// 设置token
		request.getSession().setAttribute("sessionToken", UUID.randomUUID().toString());
		System.out.println(githubInfoResult);
		jr.setResult("data", githubInfoResult);
		String r = JSON.toJSONString(jr);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(r);
		return null;
	}

}
