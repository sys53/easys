/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.login;

/**
 * 登陆server 接口
 * User : weixi
 * Date : 2016/10/26 15:11
 * File : LoginServer.java
 */
public interface UserLoginService {

	/**
	 * 清除一个用户的错误次数
	 * @param code
	 */
	void clearLockCount(String code);

	/**
	 * 获得用户的错误次数
	 * @param code
	 * @return
	 */
	Integer getErrorCount(String code);


	/**
	 * 免登录
	 * @param userCode
	 * @param loginPrefix 免登陆前缀，点对点-“p2pca_”,单点登录-“sso_”
     */
	void freeLogin(String userCode, String loginPrefix);

}
