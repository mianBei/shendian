package com.example.managerDao.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-06-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_account_info")
public class AccountInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台id
     */
    @TableField("platformId")
    private Integer platformId;

    /**
     * 用户信息id
     */
    @TableId(value = "userInfoId", type = IdType.AUTO)
    private Integer userInfoId;

    /**
     * userid   后台生成
     */
    @TableField("userId")
    private String userId;

    /**
     * 用户各端openid数组对象
[
   {
       appName：wxApp, //各端名称  wxApp为微信小程序
       openId: //openid
   }
]
     */
    @TableField("userOpenArr")
    private String userOpenArr;

    /**
     * 用户头像
     */
    @TableField("userHeadImage")
    private String userHeadImage;

    /**
     * 用户昵称
     */
    @TableField("userNickname")
    private String userNickname;

    /**
     * 用户账户账号
     */
    @TableField("userAccountCode")
    private String userAccountCode;

    /**
     * 用户账户密码
     */
    @TableField("userAccountPassword")
    private String userAccountPassword;

    /**
     * 用户是否绑定手机号    0：没有；1绑了
     */
    @TableField("isBindPhone")
    private Integer isBindPhone;

    /**
     * 用户绑定手机号
     */
    @TableField("bindPhone")
    private String bindPhone;

    /**
     * 用户是否开通会员   0：没有；1：开通了；
     */
    @TableField("isVip")
    private Integer isVip;

    /**
     * 用户是否绑定了商家   0：没有；1绑定了；
     */
    @TableField("isBindShop")
    private Integer isBindShop;

    /**
     * 用户是否是代理商    0：不是；1：是；
     */
    @TableField("isAgent")
    private Integer isAgent;

    /**
     * 用户是否是合伙人    0：不是；1：是；
     */
    @TableField("isPartner")
    private Integer isPartner;

    /**
     * 用户是否有代理商推荐注册  0：没有；1：有；
     */
    @TableField("isAgentRecommend")
    private Integer isAgentRecommend;

    /**
     * 推荐代理商id
     */
    @TableField("recommendAgentId")
    private Integer recommendAgentId;

    /**
     * 用户是否实名认证了   0：没有；1：认证了；
     */
    @TableField("isCertification")
    private Integer isCertification;

    /**
     * 用户真实姓名
     */
    @TableField("userRealName")
    private String userRealName;

    /**
     * 用户身份证号
     */
    @TableField("userIdCode")
    private String userIdCode;

    /**
     * 是否有合伙人推荐注册    0：没有；1：有；
     */
    @TableField("isPartnerRecommend")
    private Integer isPartnerRecommend;

    /**
     * 推荐合伙人id
     */
    @TableField("recommendPartnerId")
    private Integer recommendPartnerId;

    /**
     * 用户是否完善了资料    0：没有；1：完善了；
     */
    @TableField("isPerfectData")
    private Integer isPerfectData;

    /**
     * 用户是否关注了公众号   0：没有；1：关注了；
     */
    @TableField("isDiyihh")
    private Integer isDiyihh;

    /**
     * 用户是否下载了app    0：没有；1：下载了；
     */
    @TableField("isDownloadApp")
    private Integer isDownloadApp;

    /**
     * 用户二维码
     */
    @TableField("userQRCode")
    private String userQRCode;

    /**
     * 用户发展人数
     */
    @TableField("userDevelopNum")
    private Integer userDevelopNum;

    /**
     * 用户历史搜索数组
     */
    @TableField("searchArr")
    private String searchArr;


}
