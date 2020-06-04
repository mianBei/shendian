package com.example.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Util {
    public static String SHA1(String decript) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static void main(String[] args){
       String a = SHA1("jsapi_ticket=HoagFKDcsGMVCIY2vOjf9u1GhwMM-LtMVrd2qtcbni8TX5BSILIvgry1V6BMmXl7US18k2QNVXKMhD3aiQJD2g&noncestr=423a1837282a4860&timestamp=1528706840&url=http://x.yyccl.com");
        System.out.println(a);
    }
}
