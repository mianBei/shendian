package com.example.common.wxutil;

public class WxConfig {


	/**
	 * 服务号相关信息
	 */
	public final static String APP_ID="wx9902715a98592b8b";//
	public final static String APP_SECRET = "162a928bf280dbf71e41fb5a66f1c66d";//公众号SECRET密码
	 public final static String APP_MINI_ID = "wx5d9a25d1c74a8181";//小程序APP_ID

	 public final static String APP_MINI_SECRET = "5fdf1aa6f04e531efae835406ce7bf76";//小程序SECRET密码

	 public final static String MCH_ID = "1574435861";//商户号

	 public final static String SIGN_TYPE = "MD5";//签名加密方式

	 public final static String API_KEY = "WillAtHome20200116GoodLuckFaCaiA";  //API密钥

	 public final static String CERT_PATH="/data/appserver/cert/appcert/apiclient_cert.p12";//微信支付证书存放路径地址(目前未设置)

	 //保洁订单微信支付统一接口的回调action
	 public final static String BAOJIE_PAY_NOTIFY_URL = "https://dj.krwill.cn/jiazheng/api/pay/baoJieOrderNotify.htm";//支付回调地址
	//商品订单微信支付统一接口的回调action
	public final static String GOODS_PAY_NOTIFY_URL = "https://dj.krwill.cn/jiazheng/api/pay/goodsOrderNotify.htm";//支付回调地址
	//微信基础接口地址

	 //获取全局token接口(GET)
	 public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=";
	 //用户不关注公众号获取用户信息
	 public final static String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=";
	 //公众号授权接口(GET)
	 public final static String OAUTH2_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=";
	 //公众号获取union_id
	public final static String UNION_ID_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=";
	 //小程序授权接口
	public final static String MINI_OATH_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=";
	 //刷新access_token接口（GET）
	 public final static String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
    //根据access_token 获取ticket
	public final  static String TICKET_URL="https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=";

	//获取
	/**
	 * 微信支付接口地址
	 */
	//微信支付下单统一接口(POST)
	public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	//微信退款接口(POST)
	public final static String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	//订单查询接口(POST)
	public final static String CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	//关闭订单接口(POST)
	public final static String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
	//退款查询接口(POST)
	public final static String CHECK_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	//对账单接口(POST)
	public final static String DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	//短链接转换接口(POST)
	public final static String SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
	//接口调用上报接口(POST)
	public final static String REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";

	//推送
	public final static String PUSH_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=";//推送url

	public final static String PAY_SUCCESS_TEMPLATE_ID = "";//下单成功订阅消息模板ID

	public final static String ACCEPT_ORDER_TEMPLATE_ID = "";//订单接单订阅消息模板ID

	public final static String FINISH_ORDER_TEMPLATE_ID = "";//订单完成订阅消息模板ID
}
