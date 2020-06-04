package com.example.common.util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FileUtil {
	/**
	 * 取得文件扩展名
	 * @param fileName 文件全名
	 * @return
	 */
	public static String getExtension(String fileName){
		int par=fileName.lastIndexOf(".");
		String extension="";
		if (par!=-1){
			extension=fileName.substring(par);
		}
		return extension;
	}

	/**
	 * 取得文件名
	 * @param fileName 文件全名
	 * @return
	 */
	public static String getFileName(String fileName){
		int par=fileName.lastIndexOf(".");
		String name;
		if (par==-1){
			name=fileName;
		}else{
			name=fileName.substring(0,par);
		}
		return name;
	}

	//自定义文件上传的名称
	public static String defineUploadFileName(){
		return System.currentTimeMillis()+"";
	}

	public static String defineUploadFileNameUUID(){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}


	/*public static String getAppRealPath(HttpServletRequest request){
		String appPath=request.getSession().getServletContext()
				.getRealPath("/");
		return appPath;
	}*/

	/**
	 * 创建路径规则
	 * dic 存储文件夹
	 * @return
	 */
	public static Map<String,String> getFilePath(String fileType) {
		HashMap<String,Object> urlMap = pixUrl(fileType);
		//图片上传真实相对路径
		String path = DatePathUtil.getDatePath();
		//长传路径
		String filePath = urlMap.get("upload_url").toString()+path;
		//访问路径
		String requestPath=urlMap.get("show_url").toString()+path;
		File file = new File(filePath);
		if(!file.exists()||!file.isDirectory()){
			file.mkdirs();
		}
		Map<String,String> pathMap=new HashMap<String,String>();
		pathMap.put("filePath", filePath);
		pathMap.put("requestPath", requestPath);
		return pathMap;
	}

	private static HashMap<String,Object> pixUrl(String fileType){
		HashMap<String,Object> urlMap = new HashMap<>();
		if(fileType.equals("advise")){
			urlMap.put("upload_url",ConfigUtil.getString("advise"));
			urlMap.put("show_url",ConfigUtil.getString("show_ad_img"));
		}//暂定
		return urlMap;
	}

	/**
	 * 创建1.0广告图片路径
	 * @param
	 */
	public static Map<String,String> getFilePicPath(){
		String basePath=ConfigUtil.getString("ad.img.directory");
		String requestPath=ConfigUtil.getString("img.storage_path");
		File file = new File(basePath);
		if(!file.exists()||!file.isDirectory()){
			file.mkdirs();
		}
		Map<String,String> pathMap=new HashMap<String,String>();
		pathMap.put("filePath", basePath);
		pathMap.put("requestPath", requestPath);
		return pathMap;
	}
	public static Map<String,String> getFileUploadPath(){
		Map<String,String> pathMap=new HashMap<String,String>();
		String datePath = DatePathUtil.getDatePath();
		String basePath=ConfigUtil.getString("ad_file")+datePath+  "/";
		String requestPath=ConfigUtil.getString("show_ad_file")+datePath;
		File file = new File(basePath);
		if(!file.exists()||!file.isDirectory()){
			file.mkdirs();
		}
		pathMap.put("filePath", basePath);
		pathMap.put("requestPath", requestPath);
		return pathMap;
	}

	public static String getAppRealPath(HttpServletRequest request){
		String appPath=request.getSession().getServletContext()
				.getRealPath("/");
		return appPath;
	}
	/**
	 * 创建路径规则
	 *
	 * @return
	 */
	public static Map<String,String> getFilePath(HttpServletRequest request) {
		String basePath=getAppRealPath(request);
		String currentTime = DateUtils.getDateForChar();
		String filePath = basePath+File.separator+UPLOAD_DIRECTORY+
				File.separator+currentTime+File.separator;
		String requestPath="/"+UPLOAD_DIRECTORY+"/"+currentTime+"/";
		File file = new File(filePath);
		if(!file.exists()||!file.isDirectory()){
			file.mkdirs();
		}
		Map<String,String> pathMap=new HashMap<String,String>();
		pathMap.put("filePath", filePath);
		pathMap.put("requestPath", requestPath);
		return pathMap;
	}
	/**
	 * 创建路径规则
	 * dic 存储文件夹
	 * @param to  文件所属项目
	 * @param fileType  文件类型 img-图片 video-视频
	 * @return
	 */
	public static Map<String,String> getFilePath(String to,String fileType) {
		String basePath;
		String filePath ="";
		String requestPath="";
		String currentTime=DateUtils.getDateForChar().replaceAll("-", "");
		basePath=ConfigUtil.getString("pic.storage_path");
		filePath = basePath+"/"+currentTime;
		requestPath=ConfigUtil.getString("pic.url_prefix")+currentTime+"/";
		File file = new File(filePath);
		if(!file.exists()||!file.isDirectory()){
			file.mkdirs();
		}
		Map<String,String> pathMap=new HashMap<String,String>();
		pathMap.put("filePath", filePath);
		pathMap.put("requestPath", requestPath);
		pathMap.put("path", "/"+currentTime);
		return pathMap;
	}

	public final static String UPLOAD_DIRECTORY="upload/image";

	public static void main(String[] args) {

	}
}
