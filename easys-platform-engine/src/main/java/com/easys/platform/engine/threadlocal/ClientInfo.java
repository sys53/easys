/*
 * Zjrcu.com Inc.
 * Copyright (c) 2016-2016 All Rights Reserved.
 */

package com.easys.platform.engine.threadlocal;

/**
 * 客户端用户信息接口
 * <p>
 * 本信息主要在浏鉴器访问系统，自动收信用客户信息的接口。
 * </p>
 * User: sys53
 * Date: 2016/4/22 15:18
 * version $Id: ClientInfo.java, v 0.1  15:18 Exp $
 */
public interface ClientInfo {
	void setOperator(String operator);

	String getOperator();

	void setOperatorName(String operatorName);

	String getOperatorName();

	void setOperatorOrgId(String operatorOrgId);

	String getOperatorOrgId();

	void setOperatorOrgCode(String operatorOrgCode);

	String getOperatorOrgCode();

	void setOperatorOrgName(String operatorOrgName);

	String getOperatorOrgName();

	void setUrl(String url);

	String getUrl();

	void setModuleCode(String moduleCode);

	String getModuleCode();

	void setModuleName(String moduleName);

	String getModuleName();

	void setSystemCode(String systemCode);

	String getSystemCode();

	void setSystemName(String systemName);

	String getSystemName();

	void setClientIp(String clientIp);

	String getClientIp();

	void setClientBrowser(String clientBrowser);

	String getClientBrowser();

	void setClientLocation(String clientLocation);

	String getClientLocation();

	void setClientOs(String clientOs);

	String getClientOs();

	String getLoginPhone() ;

	void setLoginPhone(String loginPhone) ;

	String getSerialNo() ;

	void setSerialNo(String serialNo);

	String getMacAddress() ;

	void setMacAddress(String macAddress) ;
}
