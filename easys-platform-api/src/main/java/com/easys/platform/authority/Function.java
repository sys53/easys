/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.authority;


import com.easys.platform.engine.kernel.FromVersion;

/**
 * 定义功能api接口
 * <p/>
 * User: sys53
 * Date: 2016/4/19 11:11
 * version $Id: Function.java, v 0.1  11:11 Exp $
 */
 public interface Function extends FromVersion {
	 String getFunctionId();

	 void setFunctionId(String functionId);

	 String getFuncName();

	 void setFuncName(String funcName);

	 String getFuncDesc();

	 void setFuncDesc(String funcDesc);

	 String getFuncUrl();

	 void setFuncUrl(String funcUrl);

	 String getFuncIcon();

	 void setFuncIcon(String funcIcon);

	 String getParentId();

	 void setParentId(String parentId);
}
