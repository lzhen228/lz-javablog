package cn.secure.web.front;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.alibaba.fastjson.JSON;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;

import cn.secure.entity.Announcement;
import cn.secure.entity.Article;
import cn.secure.entity.Message;
import cn.secure.entity.Recharge;
import cn.secure.entity.Talk;
import cn.secure.service.BlogHomeService;
import cn.secure.util.BaseController;
import cn.secure.util.CommonUtil;
import cn.secure.util.JsonResult;

@Controller
@RequestMapping("/index")

public class BlogHomeController extends BaseController {
	@Autowired
	private BlogHomeService blogHomeService;

//	@Value("#{initProperties['path']}")
	private final String PATH = "/home/lzhenVita.docx";
//	@Value("#{initProperties['password']}")
	private final String PASSWORD = "429006";

	@RequestMapping("findArticleList")
	@ResponseBody
	public String findArticleList(@RequestParam("id") Integer id, @RequestParam("falge") Integer falge,
			@RequestParam("size") Integer size, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		JsonResult jr = new JsonResult();
		List<Article> articleList = blogHomeService.findArticleList(id, falge, size);
		int count = blogHomeService.findArticleList(id, falge, null).size();
		jr.setResult("data", articleList);
		jr.setResult("count", count);

		String r = JSON.toJSONString(jr);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(r);
		return null;
	}

	@RequestMapping("findQueryArticle")
	@ResponseBody
	public String findArticleList(@RequestParam("title") String title, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		JsonResult jr = new JsonResult();
		List<Article> articleList = blogHomeService.findArticleList(title);
		jr.setResult("data", articleList);

		String r = JSON.toJSONString(jr);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(r);
		return null;
	}

	@RequestMapping("findthisArticle")
	@ResponseBody
	public String findthisArticle(@RequestParam("id") Integer id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JsonResult jr = new JsonResult();
		// 只有id是有用的
		Integer falge = 1;
		List<Article> nextArticle = new ArrayList<Article>();
		List<Article> lastArticle = new ArrayList<Article>();
		List<Article> thisArticle = blogHomeService.findArticleList(id, falge, null);
		if (thisArticle.get(0).getMinid() < id) {
			lastArticle = blogHomeService.findArticleList(id - 1, falge, null);
		} else {
			lastArticle.add(new Article()); // 防止报错
		}
		if (thisArticle.get(0).getMaxid() > id) {
			nextArticle = blogHomeService.findArticleList(id + 1, falge, null);
		} else {
			nextArticle.add(new Article()); //
		}
		jr.setResult("last", lastArticle.get(0));
		jr.setResult("next", nextArticle.get(0));
		jr.setResult("this", thisArticle.get(0));

		String r = JSON.toJSONString(jr);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(r);
		return null;
	}

	@RequestMapping("updateReadNum")
	@ResponseBody
	public String updateReadNum(@RequestParam("id") Integer id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JsonResult jr = new JsonResult();
		Boolean msg = false;
		msg = blogHomeService.updateReadNum(id);
		jr.setResult("msg", msg);
		String r = JSON.toJSONString(jr);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(r);
		return null;
	}

	@RequestMapping("updateLoveNum")
	@ResponseBody
	public String updateLoveNum(@RequestParam("id") Integer id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JsonResult jr = new JsonResult();
		Boolean msg = false;
		msg = blogHomeService.updateLoveNum(id);
		jr.setResult("msg", msg);
		String r = JSON.toJSONString(jr);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(r);
		return null;
	}

	@RequestMapping("findTalkList")
	@ResponseBody
	public String findTalkList(@RequestParam("id") Integer id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		JsonResult jr = new JsonResult();
		List<Talk> talkList = blogHomeService.findTalkList(id);
		jr.setResult("data", talkList);
		String r = JSON.toJSONString(jr);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(r);
		return null;
	}

	@RequestMapping("insertTalk")
	@ResponseBody
	public String insertTalk(@RequestParam("talkJson") String talkJson, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Talk talk = CommonUtil.formatTotTalk(talkJson);
		JsonResult jr = new JsonResult();
		Boolean msg = false;
		msg = blogHomeService.insertTalk(talk);
		jr.setResult("msg", msg);
		String r = JSON.toJSONString(jr);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(r);
		return null;
	}

	@RequestMapping("findQueryString")
	@ResponseBody
	public String findQueryString(@RequestParam("title") String title, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JsonResult jr = new JsonResult();
		List<Article> results = blogHomeService.findQueryString(title);
		jr.setResult("data", results);

		String r = JSON.toJSONString(jr);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(r);
		return null;
	}

	@RequestMapping("findMessageList")
	@ResponseBody
	public String findMessageList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JsonResult jr = new JsonResult();
		List<Message> results = blogHomeService.findMessageList();
		jr.setResult("data", results);

		String r = JSON.toJSONString(jr);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(r);
		return null;
	}

	@RequestMapping("insertMessage")
	@ResponseBody
	public String insertMessage(@RequestParam("content") String content, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JsonResult jr = new JsonResult();
		Boolean msg = false;
		msg = blogHomeService.insertMessage(content);
		jr.setResult("msg", msg);
		String r = JSON.toJSONString(jr);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(r);
		return null;
	}

	@RequestMapping("findRecharge")
	@ResponseBody
	public String findRecharge(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JsonResult jr = new JsonResult();
		List<Recharge> results = blogHomeService.findRecharge();
		jr.setResult("data", results);
		String r = JSON.toJSONString(jr);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(r);
		return null;
	}

	@RequestMapping("isPassword")
	public String isPassword(@RequestParam("webPassword") String webPassword, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JsonResult jr = new JsonResult();
		boolean msg = PASSWORD.equals(webPassword);
		jr.setResult("data", msg);
		String r = JSON.toJSONString(jr);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(r);
		return null;

	}

	@RequestMapping("download")
	public String downloadAtt(HttpServletRequest request, HttpServletResponse response) {
		CommonUtil.download(PATH, response);
		return null;
	}

	/**
	 * sendPhoto (发送短信)<br/>
	 * 
	 * @param content  短信相关参数
	 * @param request  入参
	 * @param response 出参
	 * @return
	 * @throws IOException
	 * @author ygc
	 * @since JDK 1.8
	 */
	@RequestMapping("sendtelPhoto")
	@ResponseBody
	public String sendPhoto(@RequestParam("telPhoto") String telPhoto, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JsonResult jr = new JsonResult();
		String message = "发生异常，请稍后重试"; //默认失败信息
		Boolean code = false ; 
		try {

			Credential cred = new Credential("a",
					"b");

			HttpProfile httpProfile = new HttpProfile();
			httpProfile.setEndpoint("sms.tencentcloudapi.com");

			ClientProfile clientProfile = new ClientProfile();
			clientProfile.setHttpProfile(httpProfile);

			SmsClient client = new SmsClient(cred, "", clientProfile);
													
			String params = "{\"PhoneNumberSet\":[\"+86" + telPhoto + "\"]," + "\"TemplateID\":\"704502\","
					+ "\"Sign\":\"小喵八\"," + "\"TemplateParamSet\":["+PASSWORD+"],"
					+ "\"SmsSdkAppid\":\"1400419251\"}";
			SendSmsRequest req = SendSmsRequest.fromJsonString(params, SendSmsRequest.class);
         
			SendSmsResponse resp = client.SendSms(req);
			message = resp.getSendStatusSet()[0].getMessage();	
			code = "Ok".equals(resp.getSendStatusSet()[0].getCode()) ? true :false ; 
		} catch (TencentCloudSDKException e) {
			code = false;
		}
		jr.setResult("code", code);
		jr.setResult("message", message);
		String r = JSON.toJSONString(jr);
		response.setContentType("textml;charset=UTF-8");
		response.getWriter().print(r);
		return null;
	}
	
	@RequestMapping("findAnnouncement")
	@ResponseBody
	public String findAnnouncement(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JsonResult jr = new JsonResult();	
		List<Announcement> announcement = blogHomeService.findAnnouncement();
		jr.setResult("announcement", announcement);
		String r = JSON.toJSONString(jr);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(r);
		return null;
	}

}
