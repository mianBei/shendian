package com.example.common.bean;

public interface Constants {
    /**
     * 成功返回参数
     */
    int RESULT_SUCCESS=0;
    /**
     * 失败返回参数
     */
    int RESULT_ERROR=1;
    /**
     * 成功返回描述
     */
    String RESULT_SUCCESS_CON = "获取数据成功";

    /**
     * 失败返回描述
     */
    String RESULT_ERROR_CON = "获取数据失败";

    /**
     * 用户初始信用分
     */
    int REPUTATION=100;

    /**
     * 文章检索ID
     */
    String ID="id";
    /**
     * 文章检索标题
     */
    String TITLE="title";
    /**
     * 文章检索内容
     */
    String CONTENT="content";

    /**
     * APP程序类型\r\n      1：app；2：微信小程序'
     */
    int APPTYPE=2;
    /**
     * 使用者类型 \r\n     1：用户端和商家端；3：平台端
     */
    String USERTYPE="1";

    /**
     *  用户之前是否登录过
     *  0没有
     *  1有
     */
    String userNo="0";
    String userYes="1";
}
