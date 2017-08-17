/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.user;

/**
 * 值接口，外部的数据过来后都属于的user接口的子类
 * <p/>
 * User: weixi
 * Date: 2016/4/5 10:24
 * version $Id: User.java, v 0.1  10:24 Exp $
 */
public interface User {
    /** 用户id */
    String getUserId();

    void setUserId(String userId);

    /** 用户code */
    String getCode();

    void setCode(String code);

    /** 用户名 */
    String getName();

    void setName(String name);

    String getPasswd();

    void setPasswd(String passwd);

    /** 电话 */
    String getTelephone();

    void setTelephone(String telephone);

    /** 手机 */
    String getMobile();

    void setMobile(String mobile);

    /** email */
    String getEmail();

    void setEmail(String email);

    /** 性别 true 男  false 女 */
    Boolean getSex();

    void setSex(Boolean sex);

    /** 机构code */
    void setOrgCode(String orgCode);

    String getOrgCode();

    /** 是否锁定，1锁定，0未锁定 */
     Boolean getIsLock() ;

     void setIsLock(Boolean isLock) ;

    /** 是否启用 */
    Boolean getIsEnable();

    void setIsEnable(Boolean isEnable);


    /** 是否删除，1删除，0未删除 */
     Boolean getIsDelete();

    void setIsDelete(Boolean isDelete);

    /** 手势密码 */
    String getGesturePasswd();

    void setGesturePasswd(String gesturePasswd);


    Boolean getFirstLogin();

    void setFirstLogin(Boolean firstLogin);

}
