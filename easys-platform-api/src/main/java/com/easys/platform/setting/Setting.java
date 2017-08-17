/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.setting;

import com.easys.platform.engine.kernel.FromVersion;

/**
 * 系统参数接口
 * <p/>
 * User: sys53
 * Date: 2016/4/6 13:29
 * version $Id: SystemParam.java, v 0.1  13:29 Exp $
 */
public interface Setting extends FromVersion {

	String getSystemParamId();

	void setSystemParamId(String systemParamId);

	String getParamName();

	void setParamName(String paramName);

	String getParamVal();

	void setParamVal(String paramVal);

	String getParamDesc();

	void setParamDesc(String paramDesc);

	int getOrd();

	void setOrd(int ord);

	Boolean getIsSystem();

	void setIsSystem(Boolean isSystem);

	String getParamType();

	void setParamType(String paramType);

	String getOrgCode();

	void setOrgCode(String orgCode);

	String getParamLevel();

	void setParamLevel(String paramLevel);

}
