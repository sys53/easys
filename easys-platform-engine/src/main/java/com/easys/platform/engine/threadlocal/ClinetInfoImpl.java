/*
 * Zjrcu.com Inc.
 * Copyright (c) 2016-2016 All Rights Reserved.
 */

package com.easys.platform.engine.threadlocal;

import java.io.Serializable;

/**
 * Write class comments here
 * <p/>
 * User: sys53
 * Date: 2016/4/22 16:02
 * version $Id: ClinetInfoImpl.java, v 0.1  16:02 Exp $
 */
public class ClinetInfoImpl implements ClientInfo,Serializable {

	private static final long serialVersionUID = 4101088440394396295L;

	/** 操作人柜员号 */
	private String operator;

	/** 操作人姓名 */
	private String operatorName;

	/** 所属机构Id */
	private String operatorOrgId;

	/** 所属机构代码 */
	private String operatorOrgCode;

	/** 所属机构名称 */
	private String operatorOrgName;

	/** 操作url */
	private String url;

	/** 模块编码 */
	private String moduleCode;

	/** 模块名称 */
	private String moduleName;

	/** 系统编码 */
	private String systemCode;

	/** 系统名称 */
	private String systemName;

	/** 客户端IP */
	private String clientIp;

	/** 客户端浏览器 */
	private String clientBrowser;

	/** 客户端所在地  */
	private String clientLocation;

	/** 客户端操作系统 */
	private String clientOs;

	/** 登录设备手机号 */
	private String loginPhone;

	/** 手机串号 */
	private String serialNo;

	/** MAC 地址 */
	private String macAddress;

	@Override
	public String getOperator () {
		return operator;
	}

	@Override
	public void setOperator ( String operator ) {
		this.operator = operator;
	}

	@Override
	public String getOperatorName () {
		return operatorName;
	}

	@Override
	public void setOperatorName ( String operatorName ) {
		this.operatorName = operatorName;
	}

	@Override
	public String getOperatorOrgId () {
		return operatorOrgId;
	}

	@Override
	public void setOperatorOrgId ( String operatorOrgId ) {
		this.operatorOrgId = operatorOrgId;
	}

	@Override
	public String getOperatorOrgCode () {
		return operatorOrgCode;
	}

	@Override
	public void setOperatorOrgCode ( String operatorOrgCode ) {
		this.operatorOrgCode = operatorOrgCode;
	}

	@Override
	public String getOperatorOrgName () {
		return operatorOrgName;
	}

	@Override
	public void setOperatorOrgName ( String operatorOrgName ) {
		this.operatorOrgName = operatorOrgName;
	}

	@Override
	public String getUrl () {
		return url;
	}

	@Override
	public void setUrl ( String url ) {
		this.url = url;
	}

	@Override
	public String getModuleCode () {
		return moduleCode;
	}

	@Override
	public void setModuleCode ( String moduleCode ) {
		this.moduleCode = moduleCode;
	}

	@Override
	public String getModuleName () {
		return moduleName;
	}

	@Override
	public void setModuleName ( String moduleName ) {
		this.moduleName = moduleName;
	}

	@Override
	public String getSystemCode () {
		return systemCode;
	}

	@Override
	public void setSystemCode ( String systemCode ) {
		this.systemCode = systemCode;
	}

	@Override
	public String getSystemName () {
		return systemName;
	}

	@Override
	public void setSystemName ( String systemName ) {
		this.systemName = systemName;
	}

	@Override
	public String getClientIp () {
		return clientIp;
	}

	@Override
	public void setClientIp ( String clientIp ) {
		this.clientIp = clientIp;
	}

	@Override
	public String getClientBrowser () {
		return clientBrowser;
	}

	@Override
	public void setClientBrowser ( String clientBrowser ) {
		this.clientBrowser = clientBrowser;
	}

	@Override
	public String getClientLocation () {
		return clientLocation;
	}

	@Override
	public void setClientLocation ( String clientLocation ) {
		this.clientLocation = clientLocation;
	}

	@Override
	public String getClientOs () {
		return clientOs;
	}

	@Override
	public void setClientOs ( String clientOs ) {
		this.clientOs = clientOs;
	}

	@Override
	public String getLoginPhone () {
		return loginPhone;
	}

	@Override
	public void setLoginPhone ( String loginPhone ) {
		this.loginPhone = loginPhone;
	}

	@Override
	public String getSerialNo () {
		return serialNo;
	}

	@Override
	public void setSerialNo ( String serialNo ) {
		this.serialNo = serialNo;
	}

	@Override
	public String getMacAddress () {
		return macAddress;
	}

	@Override
	public void setMacAddress ( String macAddress ) {
		this.macAddress = macAddress;
	}

	@Override
	public String toString () {
		return "ClinetInfo{" +
				"operator='" + operator + '\'' +
				", operatorName='" + operatorName + '\'' +
				", operatorOrgId='" + operatorOrgId + '\'' +
				", operatorOrgCode='" + operatorOrgCode + '\'' +
				", operatorOrgName='" + operatorOrgName + '\'' +
				", url='" + url + '\'' +
				", moduleCode='" + moduleCode + '\'' +
				", moduleName='" + moduleName + '\'' +
				", systemCode='" + systemCode + '\'' +
				", systemName='" + systemName + '\'' +
				", clientIp='" + clientIp + '\'' +
				", clientBrowser='" + clientBrowser + '\'' +
				", clientLocation='" + clientLocation + '\'' +
				", clientOs='" + clientOs + '\'' +
				", loginPhone='" + loginPhone + '\'' +
				", serialNo='" + serialNo + '\'' +
				", macAddress='" + macAddress + '\'' +
				'}';
	}
}
