package cn.secure.service.impl;

import static cn.secure.util.httpClientTest.getByCookie;
import static cn.secure.util.httpClientTest.httpClientGet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.cookie.Cookie;
import cn.secure.util.httpClientTest;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.hutool.crypto.digest.DigestUtil;
import cn.secure.dao.BlogHomeDao;

import cn.secure.service.BlogHomeService;

@Service("BlogsHomeService")
public class BlogHomeServiceImp implements BlogHomeService {



	@Override
	public String qianxinLogin() {
		String IP = "10.48.21.125";
        String loginKey = "Admin123";
        Map<String, Object> csrfRes = getCsrfAndCokkies();
        List<Cookie> listCookie = (ArrayList<Cookie>) csrfRes.get("cookies");
        String str = (String) csrfRes.get("csrf_token");
        String csrf_token = str.split("csrf-token")[1].split("\"")[2];
        System.out.println(csrf_token);
        System.out.println(listCookie);
        String cookie = "";
        for(Cookie cke:listCookie){
            cookie += cke.getName()+"="+cke.getValue()+";";
        }
        System.out.println(cookie);


        Map<String, Object> map = new HashMap<>();
//        map.put("data_source",1);
//        map.put("alarm_sip","");
//        map.put("attack_sip","");
//        map.put("start_time", 1617483132*1000);
//        map.put("end_time",1618138332*1000);
        map.put("csrf_token",csrf_token);
        String urlData = "https://"+IP+":443/skyeye/dashboard/bigscreen";
        String finalRes = getByCookie(urlData, map, "utf-8", csrf_token, cookie);
		return finalRes;
	}
	
	 public static Map<String, Object> getCsrfAndCokkies(){
	        String IP = "10.48.21.125";
	        String loginKey = "Admin123";
	        String url = "https://"+IP+":443/skyeye/v1/admin/auth";
	        String access_token = getAccessToken(IP,loginKey);
	        Map<String, Object> map = new HashMap<>();
	        map.put("token",access_token);
	        Map<String, Object> res = httpClientGet(url, map, "utf-8", null, null);
	        //此处的是一个页面要获取其中的csrf_token
	        return res;
	    }

	    public static String getAccessToken(String IP, String loginKey){
	        String url = "https://"+IP+":443/skyeye/v1/admin/auth";
	        String username= "tapadmin";
	        String client_id_random = "mNSLP9UJCtBHtegjDPJnK3v";
	        String client_secret_random = "3460681205014671737";
	        String timestamp = System.currentTimeMillis()/1000+"";
	        String idConn= client_id_random + "|" + loginKey;
	        String secretConn = client_secret_random + "|" + loginKey;
	        String CLIENT_ID = DigestUtil.sha256Hex(idConn);
	        String CLIENT_SECRET = DigestUtil.sha256Hex(secretConn);
//	        String raw_str = "{\"client_id\":"+CLIENT_ID+",\"username\":"+username+"}"+timestamp+CLIENT_SECRET;
	        Map<String, Object> map1 = new HashMap<>();
	        map1.put("client_id",CLIENT_ID);
	        map1.put("username",username);
//	        System.out.println(map1);
	        String jsonStr = JSON.toJSONString(map1);
//	        System.out.println("jsonStr"+jsonStr);
	        String raw_str = jsonStr+timestamp+CLIENT_SECRET;
	        String xAuthorization = DigestUtil.sha256Hex(raw_str);

	        Map<String, Object> params = new HashMap<>();
	        params.put("client_id",CLIENT_ID);
	        params.put("username",username);
	        String res = null;
	        try {
	            res = httpClientTest.httpClientPost(url, params, "utf-8", xAuthorization, timestamp);
	        } catch (Exception e) {
	            System.out.println("ERROR");
	            e.printStackTrace();
	        }
//	        System.out.println(res);
//	        System.out.println(timestamp);
//	        System.out.println("cid------"+CLIENT_ID);
//	        System.out.println("SECRET------"+CLIENT_SECRET);
//	        System.out.println("auth------"+xAuthorization);

	        return JSONObject.parseObject(res).get("access_token").toString();
	    }


}
