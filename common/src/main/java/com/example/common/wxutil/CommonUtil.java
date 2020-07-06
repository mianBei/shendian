/**
 * 
 */
package com.example.common.wxutil;

import com.example.common.util.UUIDGenerator;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;


public class CommonUtil {
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	/**
	 * 发送https请求
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return 返回微信服务器响应的信息
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("content-type", "application/x-www-form-urlencoded"); 
			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			return buffer.toString();
		} catch (ConnectException ce) {
			log.error("连接超时：{}", ce);
		} catch (Exception e) {
			log.error("https请求异常：{}", e);
		}
		return null;
	}

	public static void getSessionKey(HashMap<String,Object> map){
		String access_token = WxConfig.MINI_OATH_URL+ map.get("appId")+"&secret="+ map.get("appSecret")+"&js_code="+ map.get("code")+"&grant_type=authorization_code";
		String result = CommonUtil.httpsRequest(access_token,"GET",null);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String session_key = jsonObject.get("session_key").toString();
		String openid = (String) jsonObject.get("openid");
		map.put("openid",openid);
		map.put("session_key",session_key);
		if (jsonObject.containsKey("unionid")){
			if (jsonObject.get("unionid")!=null){
				map.put("union_id",jsonObject.getString("unionid"));
			}
		}
	}
	
	public static String urlEncodeUTF8(String source){
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String sort(String token, String timestamp, String nonce) {
		String[] strArray = { token, timestamp, nonce };
		Arrays.sort(strArray);

		StringBuilder sbuilder = new StringBuilder();
		for (String str : strArray) {
			sbuilder.append(str);
		}

		return sbuilder.toString();
	}

	/**
	 * 获取公众号的AccessToken
	 */
	public static String getAccessToken(){
		String token_url = WxConfig.TOKEN_URL+ WxConfig.APP_ID+"&secret="+ WxConfig.APP_SECRET;
		JSONObject object = JSONObject.fromObject(CommonUtil.httpsRequest(token_url,"GET",null));
		log.info(object.toString());
		String accessToken = object.getString("access_token");
		return accessToken;
	}

	public static String getTicket(){
		String accessToken=getAccessToken();
		String url=WxConfig.TICKET_URL+accessToken;
		JSONObject object = JSONObject.fromObject(CommonUtil.httpsRequest(url,"GET",null));
		log.info(object.toString());
		if(object.getString("errcode").equals("0")){
			String ticket = object.getString("ticket");
			return ticket;
		}else{
			throw new RuntimeException(object.getString("errmsg"));
		}
	}

	public static Map<String,String> getSignature(String requestUrl){
		String ticket=getTicket();
		String noncestr= UUIDGenerator.getUUID20();
		String timestamp=System.currentTimeMillis()/1000+"";
        return sign(ticket,requestUrl,noncestr,timestamp);
	}

	public static Map<String, String> sign(String jsapi_ticket, String url,String nonce_str,String timestamp) {
		Map<String, String> ret = new HashMap<String, String>();
		String string1;
		String signature = "";

		//注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket +
				"&noncestr=" + nonce_str +
				"&timestamp=" + timestamp +
				"&url=" + url;
		System.out.println(string1);

		try
		{
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);
        ret.put("appId", WxConfig.APP_ID);
		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash)
		{
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
}
