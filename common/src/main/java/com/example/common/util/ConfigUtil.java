package com.example.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
	
	static Properties properties;
	
	static{
		properties=new Properties();
		InputStream is= ConfigUtil.class.getResourceAsStream("/config.properties");
		try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getString(String name){
		return properties.getProperty(name);
	}
	public static Properties returnProperties(String path){
		try {
			properties=new Properties();
			properties.load(ConfigUtil.class.getResourceAsStream(path));

		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		return properties;
	}
	
	public static void main(String[] args) {
		//Properties properties = returnProperties("/config.properties");
		//System.out.println(properties.getProperty("url_prefix"));
		System.out.println(ConfigUtil.getString("pic.url_prefix"));
	}

}
