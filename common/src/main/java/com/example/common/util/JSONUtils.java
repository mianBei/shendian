package com.example.common.util;


import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.*;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.*;

/**
 * 
 * @ClassName: JSONUtils 
 * @Description: JSON工具类
 */
public class JSONUtils
{

	/**
	 *
	 * <p>Description:将json字符串转换成bean对象</p>
	 * @title json2bean
	 * @param json json字符串
	 * @return
	 * @
	 * @return Object
	 */
	public static Object json2Obj(String json)  {
		JSONObject jsonObject = JSONObject.fromObject(json);
		Object obj=JSONObject.toBean(jsonObject);
		return obj;
	}

	/**
	 *
	 * <p>Description:将bean对象转换成json</p>
	 * @title bean2json
	 * @param obj
	 * @return
	 * @
	 * @return String
	 */
	public static String bean2json(Object obj)  {
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Timestamp.class, DateJsonValueProcessor.getInstance());//DateJsonValueProcessor 用于处理时间类型数据转为String型
		config.registerJsonValueProcessor(Date.class, DateJsonValueProcessor.getInstance());//DateJsonValueProcessor 用于处理时间类型数据转为String型
		JSONObject jsonObject = JSONObject.fromObject(obj,config);
		return jsonObject.toString();
	}

	/**
	 *
	 * <p>Description:将json字符串转换成bean对象</p>
	 * @title json2bean
	 * @param json json字符串
	 * @param clazz bean的Class类对象
	 * @return
	 * @
	 * @return Object
	 */
	public static Object json2bean(String json,Class clazz)  {
		net.sf.json.util.JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss" }));
		JSONObject jsonObject = JSONObject.fromObject(json);
		Object obj=JSONObject.toBean(jsonObject, clazz);
		return obj;
	}

	/**
	 *
	 * <p>Description:将json字符串转换成动态bean</p>
	 * @title json2DynaBean
	 * @param json
	 * @return
	 * @
	 * @return Object
	 */
	public static Object json2DynaBean(String json)  {
		JSON jo = JSONSerializer.toJSON(json);
		Object obj = JSONSerializer.toJava(jo);//MorphDynaBean		
		return obj;
		//PropertyUtils.getProperty(obj, "属性名").toString();  可通过该方法调用对象中的属性		
		//PropertyUtils 包路径 org.apache.commons.beanutils.PropertyUtils;
	}

	/**
	 *
	 * <p>Description:将json字符串转换成动态bean 第二种方式</p>
	 * @title json2DynaBean2
	 * @param json
	 * @return
	 * @
	 * @return Object
	 */
	public static JSONObject json2DynaBean2(String json)  {
		JSON jo = JSONSerializer.toJSON(json);
		JSONObject jsonObject = JSONObject.fromObject(json);
		return jsonObject;
		//jsonObject.getString("属性名");  可通过该方法调用对象中的属性		
		//jsonObject.getInt("属性名");
		//jsonObject.getLong("属性名");
		//jsonObject.getDouble("属性名")
	}

	/**
	 *
	 * <p>Description:将数组对象转换成json</p>
	 * @title arrayObj2json
	 * @param array
	 * @return
	 * @
	 * @return String
	 */
	public static String arrayObj2json(Object[] array)  {
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Timestamp.class, DateJsonValueProcessor.getInstance());//DateJsonValueProcessor 用于处理时间类型数据转为String型
		JSONArray jsonArray = JSONArray.fromObject(array,config);
		return jsonArray.toString();
	}

	/**
	 *
	 * <p>Description:将json字符串转换成数组对象</p>
	 * @title json2arrayObj
	 * @param json json字符串
	 * @param clazz bean的Class类对象
	 * @return
	 * @
	 * @return Object[]
	 */
	public static Object[] json2arrayObj(String json,Class clazz) {
		net.sf.json.util.JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss" }));
		JSONArray jsonArray = JSONArray.fromObject(json);
		Object[] objs=(Object[]) JSONArray.toArray(jsonArray, clazz);
		return objs;
	}

	/**
	 *
	 * <p>Description:将List对象转换成json</p>
	 * @title list2json
	 * @param list
	 * @return
	 * @return String
	 */
	public static String list2json(List list)  {
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Timestamp.class, DateJsonValueProcessor.getInstance());//DateJsonValueProcessor 用于处理时间类型数据转为String型
		JSONArray jsonArray=JSONArray.fromObject(list,config);
		return jsonArray.toString();
	}

	/**
	 *
	 * <p>Description: 将json对象转换成list</p>
	 * @title json2list
	 * @param json json字符串
	 * @param clazz bean的Class类对象
	 * @return
	 * @
	 * @return List
	 */
	public static List json2list(String json,Class clazz)  {
		net.sf.json.util.JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss" }));
		JSONArray jsonArray = JSONArray.fromObject(json);
		List list = JSONArray.toList(jsonArray, clazz);
		return list;
	}

	public static List json2list(String json)  {
		JSONArray jsonArray = JSONArray.fromObject(json);
		List list = JSONArray.toList(jsonArray);
		return list;
	}

	/**
	 * 该方法和json2list方法功能基本一样
	 * <p>Description:将json对象转换成Collection</p>
	 * @title json2Collection
	 * @param json json字符串
	 * @param clazz bean的Class类对象
	 * @return
	 * @
	 * @return Collection
	 */
	public static Collection json2Collection(String json,Class clazz)  {
		net.sf.json.util.JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss" }));
		JSONArray jsonArray = JSONArray.fromObject(json);
		Collection con = JSONArray.toCollection(jsonArray, clazz);
		return con;
	}

	/**
	 *
	 * <p>Description:将Map对象转换成json</p>
	 * @title map2json
	 * @param map
	 * @return
	 * @return String
	 */
	public static String map2json(Map map)  {
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Timestamp.class, DateJsonValueProcessor.getInstance());//DateJsonValueProcessor 用于处理时间类型数据转为String型
		JSONObject JsonObject = JSONObject.fromObject(map,config);
		return JsonObject.toString();
	}

	public static String mapToJson(Map map){
		Map resultMap = new HashMap<String,Object>();
		resultMap.put("obj", map);
		resultMap.put("flag", "true");
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Timestamp.class, DateJsonValueProcessor.getInstance());//DateJsonValueProcessor 用于处理时间类型数据转为String型
		JSONObject JsonObject = JSONObject.fromObject(resultMap,config);
		return JsonObject.toString();
	}

	/**
	 *
	 * <p>Description:将json转换成map对象</p>
	 * @title json2map
	 * @param json
	 * @return
	 * @
	 * @return Map
	 */
	public static Map json2map(String json)  {
		JSONObject jsonObject = JSONObject.fromObject(json);
		Map map = (Map)JSONObject.toBean(jsonObject, Map.class);
		return map;
	}

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		/*
		BaseRbacOrg org1=new BaseRbacOrg();		
		org1.setName("机构1");
		org1.setOrgCode("11");
		
		BaseRbacOrg org2=new BaseRbacOrg();		
		org2.setName("机构2");
		org2.setOrgCode("22");
		
		Map map=new TreeMap();
		map.put("a", org1);
		map.put("b", org2);
		
		System.out.println(JSONUtils.map2json(map));
		*/
		String json="{\"a\":{\"name\":\"机构1\",\"orgCode\":\"11\",\"orgId\":0}, \"b\":{\"name\":\"机构2\",\"orgCode\":\"22\",\"orgId\":0}}";
		Map<String,MorphDynaBean> map= JSONUtils.json2map(json);
		MorphDynaBean obj=map.get("a");
		String name=PropertyUtils.getProperty(obj, "name").toString();
//		System.out.println(name);
	}
}
