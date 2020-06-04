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
    String RESULT_SUCCESS_CON = "返回成功";

    /**
     * 失败返回描述
     */
    String RESULT_ERROR_CON = "返回失败";

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
}
