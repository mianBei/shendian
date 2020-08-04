package com.example.common.bean;

public interface Constants {
    /**
     * 成功返回参数
     */
    int RESULT_SUCCESS=1;
    /**
     * 失败返回参数
     */
    int RESULT_ERROR=0;
    /**
     * 成功返回描述
     */
    String RESULT_SUCCESS_CON = "成功!";

    /**
     * 失败返回描述
     */
    String RESULT_ERROR_CON = "失败!";

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
    String USERNO="0";
    String USERYES="1";

    /**
     * 主菜单为0
     */
    String ISMUNE = "0";

    /**
     * 非主菜单
     */
    String ISNOTMUNE = "1";

    String NAME="_name";
    String URL="_url";

    /**
     * 菜单主要类
     */
    String PARENTID = "0";

    /**
     * 0未读1已读
     */
    int ISREAD = 0;
    /**
     * 0已删除1未删除
     */
    int ISDEL  = 1;

    int ISDELYES=0;

    int PROVINCE=0;
}
