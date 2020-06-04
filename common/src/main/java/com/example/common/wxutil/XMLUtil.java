/**
 * 
 */
package com.example.common.wxutil;


import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class XMLUtil {
	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws IOException
	 */
	public static Map doXMLParse(String strxml) throws JDOMException, IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		
		Map m = new HashMap();
		
		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = XMLUtil.getChildrenText(children);
			}
			
			m.put(k, v);
		}
		
		//关闭流
		in.close();
		
		return m;
	}
	
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(XMLUtil.getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}
	public static void main(String args[]){
		Map m = new HashMap();
		String xml = "<xml><appid><![CDATA[wxb7e4e5e3cd1660a2]]></appid>" +
				"<attach><![CDATA[倚云仓支付]]></attach>" +
				"<bank_type><![CDATA[CMB_DEBIT]]></bank_type>" +
				"<cash_fee><![CDATA[1]]></cash_fee>" +
				"<device_info><![CDATA[WEB]]></device_info>" +
				"<fee_type><![CDATA[CNY]]></fee_type>" +
				"<is_subscribe><![CDATA[Y]]></is_subscribe>" +
				"<mch_id><![CDATA[1466489502]]></mch_id>" +
				"<nonce_str><![CDATA[15474338637813]]></nonce_str>" +
				"<openid><![CDATA[oea7o1PVQWbW8_v3fHc4uA_wuCU4]]></openid>" +
				"<out_trade_no><![CDATA[15474338637813]]></out_trade_no>" +
				"<result_code><![CDATA[SUCCESS]]></result_code>" +
				"<return_code><![CDATA[SUCCESS]]></return_code>" +
				"<sign><![CDATA[689B3403EE0B71271BB7D39BAE50CDE2]]></sign>" +
				"<time_end><![CDATA[20190114104435]]></time_end>" +
				"<total_fee>1</total_fee>" +
				"<trade_type><![CDATA[JSAPI]]></trade_type>" +
				"<transaction_id><![CDATA[4200000269201901145885590367]]></transaction_id>";
		try {
			m = doXMLParse(xml);
		}catch (Exception e){

		}
		System.out.print(m);

	}
}
