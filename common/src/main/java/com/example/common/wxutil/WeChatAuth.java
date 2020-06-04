package com.example.common.wxutil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WeChatAuth {
    private static final String AUTHORIZATION_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";
    public static String authorize(String redirectUri, boolean isGetUserInfo, String state) {
        String scope=isGetUserInfo? "snsapi_userinfo"	: "snsapi_userinfo" ;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("appid", WxConfig.APP_ID));
        params.add(new BasicNameValuePair("redirect_uri", redirectUri));
        params.add(new BasicNameValuePair("response_type", "code"));
        params.add(new BasicNameValuePair("scope", scope));
        params.add(new BasicNameValuePair("state", state));
        return generateUrl(AUTHORIZATION_URL, params, "#wechat_redirect");
    }
    private static String generateUrl(String baseUrl, List<NameValuePair> params, String prefix) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(baseUrl);
        if (params != null) {
            Iterator<NameValuePair> iterator = params.iterator();
            int n = 0;
            while (iterator.hasNext()) {
                if (n++ == 0) {
                    buffer.append("?");
                } else {
                    buffer.append("&");
                }
                NameValuePair entry = iterator.next();
                buffer.append(entry.getName()).append("=")
                        .append(entry.getValue());
            }
        }
        if (prefix!=null && !"".equals(prefix)) {
            buffer.append(prefix);
        }
        return buffer.toString();
    }
}
