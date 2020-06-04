package com.example.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UUIDGenerator {

	public static synchronized String getUUID()
	  {
	    String s = UUID.randomUUID().toString();
	    return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + 
	      s.substring(19, 23) + s.substring(24);
	  }

	  public static synchronized String getUUID20()
	  {
	    String s = UUID.randomUUID().toString();
	    return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23);
	  }

	  public static int getDateUUID()
	  {
	    String s = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	    Integer i = Integer.valueOf(s);
	    return i.intValue();
	  }

	  public static synchronized String getUID() {
	    String s = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
	    return s;
	  }
	public static synchronized String getRandomUID() {
		String s = new SimpleDateFormat("yyMMddHHmmSSS").format(new Date());
		return s+(int)(Math.random()*90000+10000);
	}

	  public static synchronized long getTimeStamp()
	  {
	    return System.currentTimeMillis();
	  }

	  public static void main(String[] arg) {
	    for (int i = 0; i < 100; i++)
		  System.out.println(getRandomUID());
	  }
}
