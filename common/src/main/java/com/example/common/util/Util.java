package com.example.common.util;

import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

public class Util {


	//封装Httpservelt 访问参数
	public static HashMap<String,Object> genHmParam(HttpServletRequest request){
		HashMap<String,Object> requestMap = new HashMap<String,Object>();
		Enumeration<String> params = request.getParameterNames();
		while(params.hasMoreElements()){
			String param = params.nextElement();
			String value = request.getParameter(param).trim();
			if(value!=null){
				try {
					if(value.equals(new String(value.getBytes("iso8859-1"),"iso8859-1"))){
						value=new String(value.getBytes("iso8859-1"),"utf-8");
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			requestMap.put(param, value);
		}
		return requestMap;
	}

	//生成一个编码格式 yyyyMMddHHmmss+随机三位数
	public static String genCode(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String code = sdf.format(new Date())+(int)(Math.random()*1000);
		return code;
	}

	//生成6位短信验证码
	public static String genPhoneCode(){
		String code = (int)(Math.random()*1000000)+"";
		return code;
	}
	/**
	 *加密
	 * @return
	 */
	public static String encryptLoginParam(String password) throws Exception{
		MessageDigest md5 = null;
		md5 = MessageDigest.getInstance("MD5");
		byte[] bytes = md5.digest(password.toString().getBytes("UTF-8"));
		return new BASE64Encoder().encode(bytes);
	}
	public static String randomCode(){
		String date=(System.currentTimeMillis()/1000)+"";
		String code=(int)(Math.random()*1000)+"";
		while (code.length()<3){
			code="0"+code;
		}
		code = date+code;
		return code;
	}

	/**
	 *
	 * @param b
	 * @param format true 表示保留2为小数 false 表示不强制保留2为小数，小数点后面为0时去掉末尾为0的部分
	 * @return
	 */
	public static String doubleToString(double b,boolean format){
		String result ="";
		//1.先保留两位小数
		BigDecimal bd = new BigDecimal(b);
		b=bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		result=b+"";
		if(!format){
			if(result.indexOf(".")!=-1){
				while (result.endsWith("0")){
					result=result.substring(0,result.length()-1);
				}
				if(result.endsWith(".")){
					result=result.substring(0,result.length()-1);
				}
			}
		}else{
			if(result.endsWith(".0")){
				result=result+"0";
			}
		}
		return result;
	}

	//生成保洁订单编号
	public static String getBaoJieOrderNumber(){
		return "BJ"+genCode();
	}
	/**
	 * 获取真实的图片路径
	 * @param pictures
	 * @param prefix
	 * @param split  分割字符
	 * @return
	 */
	public static String getWholePicturePath(String pictures,String prefix,String split){
		StringBuilder result= new StringBuilder();
		split= StringUtils.catchNull(split,",");
		prefix= StringUtils.catchNull(prefix,"");
		if(pictures!=null&&pictures.length()>0){
			if(pictures.startsWith("https") || pictures.startsWith("http")){
				return pictures;
			}
			String[] picArr=pictures.split(split);
			int index = 0;
			for(String pic:picArr){
				String rightPad=pic.substring(pic.lastIndexOf("/")+1,pic.length());
				String leftPad=pic.substring(0,pic.lastIndexOf("/")+1);
				pic = ConfigUtil.getString("pic.url_prefix")+leftPad+prefix+rightPad;
				if(index==picArr.length-1){
					result.append(pic);
				}else{
					result.append(pic).append("|");
				}
				index++;
			}
		}
		return result.toString();
	}
	public static List<String> getWholePictureArrayPath(String pictures, String prefix, String split,String url_prefix){
		List<String> picArrList = new ArrayList<>();
		split= StringUtils.catchNull(split,",");
		prefix= StringUtils.catchNull(prefix,"");
		if(pictures!=null&&pictures.length()>0){
			String[] picArr=pictures.split(split);
			for(String pic:picArr){
				if(pic.indexOf("http")==-1){
					String rightPad=pic.substring(pic.lastIndexOf("/")+1,pic.length());
					String leftPad=pic.substring(0,pic.lastIndexOf("/")+1);
					//pic =Constants.IMG_PREFIX+leftPad+prefix+rightPad;
					pic = url_prefix+leftPad+prefix+rightPad;
				}
				picArrList.add(pic);
			}
		}
		return picArrList;
	}

	/**
	 * 分页
	 * @param hm
	 * @return
	 */
	public static int page(HashMap<String,Object> hm){
		int page=Integer.parseInt(StringUtils.catchNull(hm.get("page"), "1"));
		int rows=Integer.parseInt(StringUtils.catchNull(hm.get("rows"), "10"));
		int start=(page-1)*rows;
		hm.put("start", start);
		hm.put("rows", rows);
		return start;
	}
	
	public static void main(String[] args) throws Exception{
		/*System.out.println(encryptLoginParam("admin123"));*/

	}


}
