package cn.secure.util;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;


public class httpClientTest {
    private static CookieStore cookieStore;
    /**
     * post请求
     */
    public static String httpClientPost(String urlParam, Map<String, Object> params, String charset, String auth, String timestamp) {
        StringBuffer resultBuffer = null;
//        HttpClient client = new DefaultHttpClient();

        CloseableHttpClient client = null;
        try {
            SSLConnectionSocketFactory scsf = new SSLConnectionSocketFactory(
                    SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build(), NoopHostnameVerifier.INSTANCE
            );
            client = HttpClients.custom().setSSLSocketFactory(scsf).build();
        }catch (Exception e){
            System.out.println("HTTPClient ERROR");
            e.printStackTrace();
        }


        HttpPost httpPost = new HttpPost(urlParam);
        //构造消息头
//        httpPost.setHeader("Content-type", "application/json; charset=utf-8");
        httpPost.setHeader("X-Authorization", auth);
        httpPost.setHeader("X-Timestamp", timestamp);


        // 构建请求参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, Object> elem = iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(), String.valueOf(elem.getValue())));
        }
//        System.out.println("list---"+list);
        BufferedReader br = null;
        try {
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
//                System.out.println(entity.toString());
            }
            HttpResponse response = client.execute(httpPost);
            // 读取服务器响应数据
            resultBuffer = new StringBuffer();
            br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String temp;
            while ((temp = br.readLine()) != null) {
                resultBuffer.append(temp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                    throw new RuntimeException(e);
                }
            }
        }
        return resultBuffer.toString();
    }

    /**
     * get请求
     */
    public static Map<String, Object> httpClientGet(String urlParam, Map<String, Object> params, String charset, String csrf_token, List<Cookie> cookieList) {
        Map<String, Object> result = new HashMap<>();
        StringBuffer resultBuffer = null;
//        HttpClient client = new DefaultHttpClient();
        cookieStore = new BasicCookieStore();

        CloseableHttpClient client = null;
        try {
            SSLConnectionSocketFactory scsf = new SSLConnectionSocketFactory(
                    SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build(), NoopHostnameVerifier.INSTANCE
            );
            client = HttpClients.custom().setSSLSocketFactory(scsf).setDefaultCookieStore(cookieStore).build();
        }catch (Exception e){
            System.out.println("HTTPClient ERROR");
            e.printStackTrace();
        }

        BufferedReader br = null;
        // 构建请求参数
        StringBuffer sbParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (Entry<String, Object> entry : params.entrySet()) {
                sbParams.append(entry.getKey());
                sbParams.append("=");
                try {
                    sbParams.append(URLEncoder.encode(String.valueOf(entry.getValue()), charset));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                sbParams.append("&");
            }
        }
        if (sbParams != null && sbParams.length() > 0) {
            urlParam = urlParam + "?" + sbParams.substring(0, sbParams.length() - 1);
        }
        HttpGet httpGet = new HttpGet(urlParam);
        if(csrf_token !=null && cookieList != null){
            httpGet.setHeader("csrf_token",csrf_token);
            httpGet.setHeader("cookie", String.valueOf(cookieList));
        }

        try {
            HttpResponse response = client.execute(httpGet);
            // 读取服务器响应数据
            br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String temp;
            resultBuffer = new StringBuffer();
            while ((temp = br.readLine()) != null) {
                resultBuffer.append(temp);
            }
            result.put("csrf_token", resultBuffer.toString());
            //获取cookies
            List<Cookie> cookies = cookieStore.getCookies();
            result.put("cookies", cookies);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
//        return resultBuffer.toString();
    }

    public static String getByCookie(String urlParam, Map<String, Object> params, String charset, String csrf_token, String cookieList) {
//        Map<String, Object> result = new HashMap<>();
        StringBuffer resultBuffer = null;
//        HttpClient client = new DefaultHttpClient();
//        cookieStore = new BasicCookieStore();

        CloseableHttpClient client = null;
        try {
            SSLConnectionSocketFactory scsf = new SSLConnectionSocketFactory(
                    SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build(), NoopHostnameVerifier.INSTANCE
            );
            client = HttpClients.custom().setSSLSocketFactory(scsf).setDefaultCookieStore(cookieStore).build();
        }catch (Exception e){
            System.out.println("HTTPClient ERROR");
            e.printStackTrace();
        }

        BufferedReader br = null;
        // 构建请求参数
        StringBuffer sbParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (Entry<String, Object> entry : params.entrySet()) {
                sbParams.append(entry.getKey());
                sbParams.append("=");
                try {
                    sbParams.append(URLEncoder.encode(String.valueOf(entry.getValue()), charset));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                sbParams.append("&");
            }
        }
        if (sbParams != null && sbParams.length() > 0) {
            urlParam = urlParam + "?" + sbParams.substring(0, sbParams.length() - 1);
        }
        HttpGet httpGet = new HttpGet(urlParam);
        if(csrf_token !=null && cookieList != null){
//            httpGet.setHeader("csrf_token",csrf_token);
            httpGet.setHeader("cookie", cookieList);
        }

        try {
            HttpResponse response = client.execute(httpGet);
            // 读取服务器响应数据
            br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String temp;
            resultBuffer = new StringBuffer();
            while ((temp = br.readLine()) != null) {
                resultBuffer.append(temp);
            }
//            result.put("csrf_token", resultBuffer.toString());
            //获取cookies
//            List<Cookie> cookies = cookieStore.getCookies();
//            result.put("cookies", cookies);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                    throw new RuntimeException(e);
                }
            }
        }
        return resultBuffer.toString();
    }


}
