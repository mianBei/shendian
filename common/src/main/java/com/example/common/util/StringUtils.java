package com.example.common.util;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class StringUtils{

	public static boolean startsWith(String str, String prefix)
	{
		return startsWith(str, prefix, false);
	}

	public static boolean startsWithIgnoreCase(String str, String prefix)
	{
		return startsWith(str, prefix, true);
	}

	public static boolean startsWith(String str, String prefix,
			boolean ignoreCase)
	{
		return startsOrEndsWith(str, prefix, ignoreCase, false);
	}

	public static boolean endsWith(String str, String suffix)
	{
		return endsWith(str, suffix, false);
	}

	public static boolean endsWithIgnoreCase(String str, String suffix)
	{
		return endsWith(str, suffix, true);
	}

	public static boolean endsWith(String str, String prefix, boolean ignoreCase)
	{
		return startsOrEndsWith(str, prefix, ignoreCase, true);
	}

	private static boolean startsOrEndsWith(String str, String subStr,
			boolean ignoreCase, boolean endWidth)
	{
		if (str == null || subStr == null)
		{
			return (str == null && subStr == null);
		}
		if (subStr.length() > str.length())
		{
			return false;
		}
		int strOffset = 0;
		if (endWidth)
		{
			strOffset = str.length() - subStr.length();
		}
		return str.regionMatches(ignoreCase, strOffset, subStr, 0, subStr
				.length());
	}

	public static final String EMPTY = "";

	public static String join(String[] array)
	{
		return join(array, null);
	}

	public static String join(String[] list, String separator)
	{
		separator = separator == null ? EMPTY : separator;
		StringBuffer buff = new StringBuffer(5 * list.length);
		for (int i = 0; i < list.length; i++)
		{
			String s = list[i];
			if (i > 0)
			{
				buff.append(separator);
			}
			if (s != null)
			{
				buff.append(s);
			}
		}
		return buff.toString();

	}

	public static String[] split2Array(String s, char separatorChar)
	{
		return split2Array(s, separatorChar, false);
	}

	public static String[] split2Array(String s, char separatorChar,
			boolean trim)
	{
		if (s == null)
		{
			return null;
		}
		if (s.length() == 0)
		{
			return new String[0];
		}
		ArrayList list = new ArrayList();
		StringBuffer buff = new StringBuffer(s.length());
		for (int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			if (c == separatorChar)
			{
				String e = buff.toString();
				list.add(trim ? e.trim() : e);
				buff.setLength(0);
			} else if (c == '\\' && i < s.length() - 1)
			{
				buff.append(s.charAt(++i));
			} else
			{
				buff.append(c);
			}
		}
		String e = buff.toString();
		list.add(trim ? e.trim() : e);
		String[] array = new String[list.size()];
		list.toArray(array);
		return array;
	}
	
	
	public static String catchNull(Object obj){
		return obj==null?"":obj.toString();
	}
	
	public static String catchNull(Object obj,String str){
		return obj==null?str:obj.toString();
	}
	
	//保留2位有效数字
	public static String formatDouble(double d){
		if(d==0){
			return "0.00";
		}
		DecimalFormat df = new DecimalFormat("#.00");
		if(d>0&&d<1){
			return d<1?"0"+df.format(d):df.format(d);
		}else if(d>-1&& d<0){
			return "-0"+df.format(Math.abs(d));
		}else{
			return df.format(d);
		}
		
	}
	
	/**
	 * 
	 * @param devidend 被除数
	 * @param devide  除数
	 * @return
	 */
	public static String getRate(double devidend,double devide){
		if(devide==0) return "0%";
		double d=(devidend/devide)*100;
		BigDecimal decimal=new BigDecimal(d);
		d=decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        String r=d+"%";
		return r;
		
	}
	public static boolean isEmpty(CharSequence cs){
		return (cs == null) || (cs.length() == 0);
	}

	public static boolean isBlank(CharSequence cs)
	{
		int strLen;

		if ((cs == null) || ((strLen = cs.length()) == 0))
			return true;
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(cs.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	
	public static void main(String[] args) {
		System.out.println(formatDouble(10.126));
	}
	
}
