package com.example.common.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
* 
* 
* @ Json转换Timestamp类型 处理器
*/

public class DateJsonValueProcessor implements JsonValueProcessor{
	  private DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    private static DateJsonValueProcessor dateJsonValueProcessor=null;
	    
	    private DateJsonValueProcessor(){
	    }
		
	    public static DateJsonValueProcessor getInstance(){
	    	if(null==dateJsonValueProcessor){
	    		dateJsonValueProcessor=new DateJsonValueProcessor();
	    	}
	    	return dateJsonValueProcessor;
	    }
	    
		
	
		@Override
		public Object processArrayValue(Object value, JsonConfig jsonConfig) {
			return process(value);
		}

		
	
		@Override
		public Object processObjectValue(String key, Object value,JsonConfig jsonConfig) {
			return process(value);
		}
		
		private Object process(Object value) {
	        if (value == null) {
	            return "";
	        } else {
	            return dateFormat.format((Timestamp)value);
	        }
	    }
}
