/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.authority;

/**
 * 定义权限范围域api接口
 * <p/>
 * User: sys53
 * Date: 2016/4/19 11:08
 * version $Id: AuthScope.java, v 0.1  11:08 Exp $
 */
public interface AuthScope {

	String getAuthScopeId();

	void setAuthScopeId(String authScopeId);

	String getAuthCodeId();

	void setAuthCodeId(String authCodeId);

	String getTargetScopeExp();

	void setTargetScopeExp(String targetScopeExp);

	String getTargetScopeId();

	void setTargetScopeId(String targetScopeId);

	String getTargetScopeType();

	void setTargetScopeType(String targetScopeType);

	String getAuthScopeType();

	void setAuthScopeType(String authScopeType);

	String getRoleId();

	void setRoleId(String roleId);
}
