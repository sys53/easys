/*
 * Copyright (c) 2017-2017 Easys technology Co.,Ltd
 */

package com.easys.platform.menu;

import java.util.Collection;
import java.util.List;

/**
 * 菜单服务接口
 * <p/>
 * User: sys53
 * Date: 2016/3/30 16:49
 * version $Id: MenuServer.java, v 0.1  16:49 Exp $
 */
public interface MenuService {
	/**
	 * 注册系统菜单
	 * @param module 插件模块
	 * @param menu 菜单
	 * @return
	 */
	void register(String module, Menu menu);

	/**
	 * 批量注册菜单
	 * @param menus
	 */
	void  register(Collection<Menu> menus);


	/**
	 * 更新菜单
	 * <p>
	 *     更新菜单时根据<code>menuCode</code>来更新的
	 * </p>
	 * @param module 插件模块
	 * @param menu 菜单
	 * @return
	 */
	void update(String module, Menu menu);

	/**
	 * 删除系统菜单
	 * @param module 插件模块
	 * @param menuCode 菜单编号 是由开发时唯一确定的编号，建议规则如：<code>PluginCode_InnerMenuCode</code>
	 * 	<code>PluginCode</code>是插件KEY
	 * 	<code>InnerMenuCode</code>是插件内部的唯一菜单编号
	 * @return
	 */
	void remove(String module, String menuCode);

	/**
	 * 获取所有有效的菜单
	 * @return  <code>Menu</code>中包括多级子菜单
	 */
	List< Menu > getAll();

	/**
	 *查询手机端菜单
	 * @param menuCode
	 * @return
	 */
	List<Menu> getAllForApp(String menuCode);

	/**
	 * 更新菜单权限
	 * @param module 插件模块
	 * @param menuCode 菜单编号 是由开发时唯一确定的编号，建议规则如：<code>PluginCode_InnerMenuCode</code>
	 * @param authCodes 是所有权限代码间加上","而变成的长字符串
	 * @return
	 */
	void updateMenuAuth(String module, String menuCode, String authCodes);

	/**
	 * 插件卸载 时 删除菜单
	 * @param values
     */
	void deleteMenu(Collection<Menu> values);

	/**
	 * 根据菜单码获取菜单
	 * @param menuCode
	 * @return
	 */
	Menu findMenuByCode(String menuCode);
}
