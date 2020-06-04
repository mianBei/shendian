package com.example.common.superCon;

import com.example.common.util.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


public abstract class SuperController {

	protected Logger log = LoggerFactory.getLogger(getClass());
	

	/**
	 * 输出字符串
	 * 
	 * @param str
	 */
	protected void outString(String str, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setContentType("text/json; charset=UTF-8");
			out = response.getWriter();
			out.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 输出json字符串 
	 * 
	 * @param json
	 */
	protected void outJson(String json, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setContentType("text/json; charset=UTF-8");
			out = response.getWriter();
			out.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 将对象转换成json输出
	 * 
	 * @param obj
	 * @throws Exception
	 */
	protected void outJsonForObject(Object obj, HttpServletResponse response){
		PrintWriter out = null;
		try {
			response.setContentType("text/json; charset=UTF-8");
			out = response.getWriter();
			String json = JSONUtils.bean2json(obj);
			out.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * 将对象转换成json输出
	 * 
	 * @param map
	 * @throws Exception
	 */
	protected void outJsonForMap(Map map, HttpServletResponse response){
		PrintWriter out = null;
		try {
			response.setContentType("text/json; charset=UTF-8");
			out = response.getWriter();
			String json = JSONUtils.map2json(map);
			out.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	
	/**
	 * 将对象转换成json输出
	 * 
	 * @param list
	 * @throws Exception
	 */
	protected void outJsonList(List list, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setContentType("text/json; charset=UTF-8");
			out = response.getWriter();
			String json = JSONUtils.list2json(list);
			out.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
