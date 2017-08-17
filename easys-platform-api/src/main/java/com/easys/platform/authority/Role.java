/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.authority;

/**
 * 定义角色api接口
 * <p/>
 * User: sys53
 * Date: 2016/4/19 11:09
 * version $Id: Role.java, v 0.1  11:09 Exp $
 */
public interface Role {

	public String getRoleId();

	public void setRoleId(String roleId);

	public String getRoleCode();

	public void setRoleCode(String roleCode);

	public String getRoleName();

	public void setRoleName(String roleName);

	public String getRoleDesc();

	public void setRoleDesc(String roleDesc);
}
