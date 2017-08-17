/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.menu;


import com.easys.platform.engine.kernel.FromVersion;

import java.util.List;

/**
 * 系统菜单值接口
 * <p/>
 * User: sys53
 * Date: 2016/3/30 16:49
 * version $Id: Menu.java, v 0.1  16:49 Exp $
 */
public interface Menu extends FromVersion {

	void setParent(Menu parent);
	Menu getParent();

	void setChildren(List<Object> children);
	List<Object> getChildren();

	String getMenuId();

	void setMenuId(String menuId);

	String getMenuCode();

	void setMenuCode(String menuCode);

	String getMenuName();

	void setMenuName(String menuName);

	String getMenuAction();

	void setMenuAction(String menuAction);

	Integer getOrd();

	void setOrd(Integer ord);

	Boolean getIsEnable();

	void setIsEnable(Boolean isEnable);

	Integer getMenuLevel();

	void setMenuLevel(Integer menuLevel);

	String getModule();

	void setModule(String module);

	String getMenuIcon();
	void setMenuIcon(String menuIcon);

	String getAuthCodes();

	void  setAuthCodes(String authCode);

	String getParentId() ;

	void setParentId(String parentId);

}
