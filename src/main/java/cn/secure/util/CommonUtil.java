package cn.secure.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import cn.secure.entity.Talk;
import cn.secure.entity.TalkJson;
import net.sf.json.JSONObject;

public class CommonUtil {

	/**
	 * String转Date
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str) {
		Date date = null;
		if (null != str && !"".equals(str)) {
			// yyyy-MM-dd HH:mm:ss
			SimpleDateFormat sdf = new SimpleDateFormat(BaseConstantUtil.TRACK_TIME_FOEMATE_2);
			try {
				// String转Date
				date = sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * Date转String
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		if (null != date) {
			// yyyy-MM-dd HH:mm:ss
			SimpleDateFormat sdf = new SimpleDateFormat(BaseConstantUtil.TRACK_TIME_FOEMATE_2);
			return sdf.format(date);
		}
		return null;
	}

	/**
	 * String转Integer
	 * 
	 * @param str
	 * @return
	 */
	private static Integer stringToInteger(String str) {
		Integer integer = null;
		if (null != str && !"".equals(str)) {
			try {
				// String转Integer
				integer = Integer.parseInt(str.trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return integer;
	}

	/**
	 * String转Long转Date
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToLongToDate(String str) {
		Date date = null;
		if (null != str && !"".equals(str)) {
			Long longTiem = null;
			try {
				// String转Long
				longTiem = Long.parseLong(str.trim());
				date = new Date(longTiem);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * undefined检查
	 * 
	 * @param str
	 * @return
	 */
	private static String undefinedCheck(String str) {
		if (null != str && !"".equals(str) && !"undefined".equals(str)) {
			return str.trim();
		}
		return "";
	}

	public static Talk formatTotTalk(String formItem) {
		Talk talk = null;
		// 如果json不为空
		if (null != formItem && !"".equals(formItem)) {
			TalkJson talkJson = null;
			JSONObject talkJsonObject = JSONObject.fromObject(formItem);
			// 先转换为MaillogJson类型
			talkJson = (TalkJson) JSONObject.toBean(talkJsonObject, TalkJson.class);
			if (null != talkJson) {
				talk = new Talk();
				talk.setTalk(undefinedCheck(talkJson.getTalk()));
				talk.setHref(undefinedCheck(talkJson.getHead()) + undefinedCheck(talkJson.getHref()));
				talk.setToHref(undefinedCheck(talkJson.getToHref()));
				talk.setName(undefinedCheck(talkJson.getName()));
				talk.setToName(undefinedCheck(talkJson.getToName()));
				talk.setTime(dateToString(new Date()));
				talk.setTid(stringToInteger(undefinedCheck(talkJson.getTid() + "")));
				talk.setAid(stringToInteger(undefinedCheck(talkJson.getId() + "")));
			}
		}
		return talk;
	}

	/**
	 * 下载文件
	 */
	public static HttpServletResponse download(String path, HttpServletResponse response) {
		try {
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 取得文件的后缀名。
			String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return response;
	}
	
	 /**
     * 向指定 URL 发送 GET 方法的请求
     *
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和 URL 之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader 输入流来读取 URL 的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 GET 请求出现异常！" + e);           
        }

        // 使用 finally 块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                // logger.error("连接出错",e2);
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送 POST 方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            OutputStream outputStream = conn.getOutputStream();
            String aa = new String(param.getBytes("UTF8"),"UTF8");
            System.out.println("=========== "+aa);
            out = new PrintWriter(outputStream);
            // 发送请求参数
            out.print(aa);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            //logger.error("连接出错",e);
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }


}
