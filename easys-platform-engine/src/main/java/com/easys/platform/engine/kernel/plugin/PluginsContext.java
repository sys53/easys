package com.easys.platform.engine.kernel.plugin;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class PluginsContext {

	private static final Map< String, PluginInfo > pluginInfoMap = new HashMap< String, PluginInfo > ();

	private static final Map< String, PluginInfo > pluginLocationFileMap = new HashMap< String, PluginInfo > ();

	private static final List< String > SYSTEM_CORE_REQUIRED_PLUGINS = new ArrayList<> ();

	static {
		SYSTEM_CORE_REQUIRED_PLUGINS.add ( "plugin-params" );
		SYSTEM_CORE_REQUIRED_PLUGINS.add ( "plugin-authority" );
		SYSTEM_CORE_REQUIRED_PLUGINS.add ( "plugin-dictionary" );
		SYSTEM_CORE_REQUIRED_PLUGINS.add ( "plugin-menu" );
		SYSTEM_CORE_REQUIRED_PLUGINS.add ( "plugin-user" );
		SYSTEM_CORE_REQUIRED_PLUGINS.add ( "plugin-login" );
		SYSTEM_CORE_REQUIRED_PLUGINS.add ( "plugin-bizlog" );
//		SYSTEM_CORE_REQUIRED_PLUGINS.add ( "plugin-help" );
//		SYSTEM_CORE_REQUIRED_PLUGINS.add ( "plugin-bulletin" );
		//SYSTEM_CORE_REQUIRED_PLUGINS.add ( "plugin-audit" );
	}

	/**
	 * 初始化标识
	 */
	public static boolean initilized = false;

	/**
	 * 初始化插件信息上下文
	 */
	public static void init ( Map< String, PluginInfo > map ) {
		if ( initilized ) {
			return;
		}
		synchronized ( PluginsContext.class ) {
			if ( initilized ) {
				return;
			}
			if ( map == null ) {
				return;
			}
			pluginInfoMap.putAll ( map );
			for ( PluginInfo pluginInfo : map.values () ) {
				pluginLocationFileMap.put ( pluginInfo.getPluginLocationFile (), pluginInfo );
			}
		}
	}


	/**
	 * 获取所有插件信息
	 *
	 * @return
	 */
	public static Collection< PluginInfo > getPluginInfos () {
		return pluginLocationFileMap.values ();
	}

	/**
	 * 根据插件文件信息获取插件信息
	 *
	 * @param pluginLocationFile
	 * @return
	 */
	public static PluginInfo getPluginInfo ( String pluginLocationFile ) {
		PluginInfo pluginInfo = pluginLocationFileMap.get ( pluginLocationFile );
		if ( pluginInfo == null ) {
			String start = "/file:";
			String end   = "!/";
			if ( StringUtils.startsWithIgnoreCase ( pluginLocationFile, start ) ) {
				String key = StringUtils.substring ( pluginLocationFile, start.length (), pluginLocationFile.length () - end.length () );
				pluginInfo = pluginLocationFileMap.get ( key );
			}
		}
		return pluginInfo;
	}

	/**
	 * 根据 插件 key 获取插件信息, key 格式 : 插件名_插件版本
	 *
	 * @param pluginKey
	 * @return
	 */
	public static PluginInfo findPluginInfo ( String pluginKey ) {
		return pluginInfoMap.get ( pluginKey );
	}

	/**
	 * 清除资源
	 */
	public static void clear () {
		pluginInfoMap.clear ();
		pluginLocationFileMap.clear ();
	}

	/**
	 * 复制内核必须的插件
	 *
	 * @return
	 */
	public static List< String > copyCoreRequiredPlugins () {
		return new CopyOnWriteArrayList<> ( SYSTEM_CORE_REQUIRED_PLUGINS );
	}

}
